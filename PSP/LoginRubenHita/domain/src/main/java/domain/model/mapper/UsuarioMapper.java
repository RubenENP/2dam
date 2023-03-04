package domain.model.mapper;

import domain.model.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario u = new Usuario();

        u.setUser(rs.getString("user"));
        u.setPassword(rs.getString("pass"));
        u.setEmail(rs.getString("email"));
        u.setActivado(rs.getInt("activado"));
        u.setRole(rs.getString("role"));

        return u;
    }
}
