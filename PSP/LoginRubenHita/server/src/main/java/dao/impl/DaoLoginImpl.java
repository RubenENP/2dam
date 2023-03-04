package dao.impl;

import dao.DBConnectionPool;
import dao.DaoLogin;
import domain.model.Activacion;
import domain.model.Usuario;
import domain.model.mapper.ActivacionMapper;
import domain.model.mapper.ReaderMapper;
import domain.model.mapper.UsuarioMapper;
import domain.model.reader.Reader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class DaoLoginImpl implements DaoLogin {
    private final Pbkdf2PasswordHash passwordHash;
    private final DBConnectionPool pool;

    @Inject
    public DaoLoginImpl(Pbkdf2PasswordHash passwordHash, DBConnectionPool pool) {
        this.passwordHash = passwordHash;
        this.pool = pool;
    }

    public Either<String, Usuario> login(String user, String pass) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        Usuario u = jtm.query("SELECT * FROM loginHash WHERE user=?", new UsuarioMapper(), user)
                .stream().findFirst().orElse(null);

        Either<String, Usuario> response;

        if (u == null) {
            response = Either.left("No hay ningun usuario con ese nombre");
        } else if (passwordHash.verify(pass.toCharArray(), u.getPassword())) {
            if (u.getActivado() == 0) {
                response = Either.left("El usuario no esta activado");
            } else {
                response = Either.right(new Usuario(user, pass, u.getEmail(), u.getActivado(),u.getRole()));
            }
        } else {
            response = Either.left("La contraseña o usuario no coinciden");
        }

        return response;
    }

    public Either<String, Usuario> register(Usuario usuario) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        int readerRegistered = insertReader(usuario);

        Either<String, Usuario> response;

        if (readerRegistered > 0){
            int rId = jtm.query("SELECT * from reader WHERE name_reader = ?",
                    new ReaderMapper(), usuario.getUser()).stream().findFirst().orElse(null).getId();

            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                    pool.getDataSource()
            ).withTableName("loginHash");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("user", usuario.getUser());
            parameters.put("pass", passwordHash.generate(usuario.getPassword().toCharArray()));
            parameters.put("email", usuario.getEmail());
            parameters.put("activado", usuario.getActivado());
            parameters.put("role", usuario.getRole());
            parameters.put("id_reader", rId);

            int registered = jdbcInsert.execute(parameters);

            if (registered > 0){
                response = Either.right(usuario);
            } else {
                response = Either.left("No se ha podido registrar el usuario");
            }
        } else {
            response = Either.left("No se ha podido insertar el reader");
        }

        return response;
    }

    private int insertReader(Usuario u) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                pool.getDataSource()
        ).withTableName("reader");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", 0);
        parameters.put("name_reader", u.getUser());
        parameters.put("birth_reader", Date.valueOf(LocalDate.now()));

        return jdbcInsert.execute(parameters);
    }

    public Either<String, Activacion> activarUsuario(String codigo) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        Activacion activacion = jdbcTemplate.query("SELECT * FROM activacion WHERE cod=?", new ActivacionMapper(), codigo)
                .stream().findFirst().orElse(null);

        Either<String, Activacion> respuesta;

        if (activacion == null) {
            respuesta = Either.left("El codigo de activacion esta mal");
        } else if (Time.valueOf(LocalTime.now()).after(Time.valueOf(LocalTime.of(
                activacion.getTime().toLocalTime().getHour(),
                activacion.getTime().toLocalTime().getMinute() + 5,
                activacion.getTime().toLocalTime().getSecond())))) {

            respuesta = Either.left("Se pasó el tiempo para activar la cuenta");
        } else {
            jdbcTemplate.update("UPDATE loginHash SET activado=1 WHERE user=?", activacion.getUser());
            jdbcTemplate.update("DELETE FROM activacion WHERE cod=?", codigo);
            respuesta = Either.right(activacion);
        }

        return respuesta;
    }

    public Either<String, Usuario> comprobarUsuario(String user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        Usuario usuario = jdbcTemplate.query("SELECT * FROM loginHash WHERE user=?", new UsuarioMapper(), user)
                .stream().findFirst().orElse(null);

        Either<String, Usuario> respuesta;

        if (usuario == null) {
            respuesta = Either.left("El usuario que has introducido no existe");
        } else {
            respuesta = Either.right(usuario);
        }

        return respuesta;
    }

    public Either<String, Usuario> cambiarPassword(String nombreUsuario, String newPassword) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        int updated = jdbcTemplate.update("UPDATE loginHash SET pass=? WHERE user=?",
                passwordHash.generate(newPassword.toCharArray()), nombreUsuario);

        Either<String, Usuario> respuesta;

        if (updated==0){
            respuesta = Either.left("No se ha podido cambiar la contraseña");
        } else {
            Usuario usuario = jdbcTemplate.query("SELECT * from loginHash WHERE user=?",new UsuarioMapper(), nombreUsuario)
                    .stream().findFirst().orElse(null);
            respuesta = Either.right(usuario);
        }

        return respuesta;
    }
}
