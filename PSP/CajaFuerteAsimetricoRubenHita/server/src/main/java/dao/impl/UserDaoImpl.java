package dao.impl;

import config.ConfigurationPool;
import dao.UserDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import mappers.UserMapper;
import modelo.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final ConfigurationPool pool;
    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public UserDaoImpl(ConfigurationPool pool, Pbkdf2PasswordHash passwordHash) {
        this.pool = pool;
        this.passwordHash = passwordHash;
    }

    public List<User> getAll() {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        return jtm.query("SELECT * FROM users", new UserMapper());
    }

    public Either<String, User> register(User u) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        Either<String, User> response;

        try {
            jtm.update("INSERT INTO users (username, password, role) VALUES (?,?,?)",
                    u.getUsername(), passwordHash.generate(u.getPassword().toCharArray()), u.getRole());
            response = Either.right(u);
        } catch (Exception e) {
            response = Either.left(e.getMessage());
        }

        return response;
    }

    @Override
    public Either<String, User> login(User u) {
        Either<String, User> response;

        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        try {
            User user = jtm.query("SELECT * FROM users WHERE username=?", new UserMapper(), u.getUsername())
                    .stream().findFirst().orElse(null);

            if (user == null) {
                response = Either.left("El usuario no existe");
            } else {
                if (passwordHash.verify(u.getPassword().toCharArray(), user.getPassword())) {
                    response = Either.right(user);
                } else {
                    response = Either.left("EL usuario o la contraseña no coinciden");
                }
            }
        } catch (Exception e) {
            response = Either.left(e.getMessage());
        }

        return response;
    }

    public Either<String, User> comprobarUsuario(String caller){
        Either<String, User> response;

        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        User u = jtm.query("SELECT * FROM users WHERE username=?", new UserMapper(), caller)
                .stream().findFirst().orElse(null);

        if (u==null){
            response = Either.left("El usuario no exite");
        } else {
            response = Either.right(u);
        }

        return response;
    }
}
