package dao;

import dao.modelo.Usuario;
import dao.modelo.UsuarioEntity;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Usuario("1", "nombre", "pass", LocalDateTime.now()));
    }

    private DBConnectionPool pool;
    private Session session;

    @Inject
    public DaoUsuario(DBConnectionPool pool, Session session) {
        this.pool = pool;
        this.session = session;
    }

    public Either<ApiError, Usuario> dameUsuarioPorNombre(String nombre) {
        Usuario u = usuarios.stream()
                .filter(usuario -> usuario.getName().equals(nombre))
                .findFirst().orElse(null);
        if (u != null) {
            return Either.right(u);
        } else {
            return Either.left(new ApiError("error not found", LocalDateTime.now()));
        }
    }

    public Either<ApiError, Usuario> dameUno(String id) {
        Usuario u = usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst().orElse(null);
        if (u != null) {
            return Either.right(u);
        } else {
            return Either.left(new ApiError("error not found", LocalDateTime.now()));
        }
    }

    public List<Usuario> dameTodos() {

        return usuarios;
//        JdbcTemplate jtm = new JdbcTemplate(
//                dbConnection.getDataSource());
//
//        // select devuelve LIST
//        return jtm.query("Select * from usuarios",
//                BeanPropertyRowMapper.newInstance(Usuario.class));
    }

    public List<UsuarioEntity> dameTodosHibernate() {
        List<UsuarioEntity> a = session.createQuery("from UsuarioEntity ",UsuarioEntity.class).getResultList();

        session.close();
        // select devuelve LIST
        return a;
    }


//    @PreDestroy
//    public void cerrarSession()
//    {
//        session.close();
//    }


    public Usuario addUser(Usuario user) {

        UsuarioEntity userE = new UsuarioEntity();
        userE.setFecha(LocalDateTime.now());
        userE.setName(user.getName());
        userE.setPassword(user.getPassword());
        session.beginTransaction();
        session.save(userE);
        session.getTransaction().commit();

        return user;
    }

    public boolean borrar(String id) {
        return usuarios.remove(usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst().orElse(null));
    }
}
