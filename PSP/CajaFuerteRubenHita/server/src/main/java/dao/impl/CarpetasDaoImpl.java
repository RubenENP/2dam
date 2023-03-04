package dao.impl;

import config.ConfigurationPool;
import dao.CarpetasDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import mappers.CarpetaMapper;
import modelo.Carpeta;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CarpetasDaoImpl implements CarpetasDao {
    private final ConfigurationPool pool;
    private final Pbkdf2PasswordHash hash;

    @Inject
    public CarpetasDaoImpl(ConfigurationPool pool, Pbkdf2PasswordHash hash) {
        this.pool = pool;
        this.hash = hash;
    }

    @Override
    public Either<String, Carpeta> create(Carpeta carpeta) {
        Either<String, Carpeta> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        String query = "insert into carpetas (id, name, permiso, password, username) values (?, ?, ?, ?, ?)";
        try {
            jtm.update(query, carpeta.getId(), carpeta.getName(), carpeta.getPermission(), hash.generate(carpeta.getPassword().toCharArray()), carpeta.getUsername());
            response = Either.right(carpeta);
        }catch (Exception e){
            response = Either.left(e.getMessage());
        }

        return response;
    }

    @Override
    public Either<String, List<Carpeta>> getAll(String username) {
        Either<String, List<Carpeta>> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        try {
            String query = "SELECT * FROM carpetas WHERE username = ?";
            response = Either.right(jtm.query(query, new CarpetaMapper(), username));
        } catch (Exception e){
            response = Either.left(e.getMessage());
        }

        return response;
    }

    @Override
    public Either<String, List<Carpeta>> getAll() {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        try {
            List<Carpeta> carpetas = jtm.query("SELECT * FROM carpetas", new CarpetaMapper());
            return Either.right(carpetas);
        } catch (Exception e) {
            return Either.left(e.getMessage());
        }
    }

    @Override
    public Either<String, String> desbloquearCarpeta(int idCarpeta, String pass, String user) {
        Either<String, String> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        Carpeta c = jtm.query("SELECT * FROM carpetas WHERE id = ?", new CarpetaMapper(), idCarpeta).stream().findFirst().orElse(null);
        if(c==null){
            response = Either.left("La carpeta no existe");
        } else {
            if (hash.verify(pass.toCharArray(), c.getPassword())){
                jtm.update("INSERT INTO relacionUserCarpeta (user, id_carpeta) VALUES (?,?)",user,idCarpeta);
                response = Either.right("Carpeta desbloqueada");
            } else {
                response = Either.left("La contaseña que has introducido es incorrecta.");
            }
        }


        return response;
    }

    @Override
    public Either<String, Carpeta> get(int id) {
        Either<String, Carpeta> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());

        Carpeta c = jtm.query("SELECT * FROM carpetas WHERE id = ?", new CarpetaMapper(), id).stream().findFirst().orElse(null);

        if(c==null){
            response = Either.left("La carpeta no existe");
        } else {
            response = Either.right(c);
        }
        return response;
    }
}
