package mappers;

import modelo.Carpeta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarpetaMapper implements RowMapper<Carpeta> {
    public Carpeta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Carpeta carpeta = new Carpeta();
        carpeta.setId(rs.getInt("id"));
        carpeta.setName(rs.getString("name"));
        carpeta.setPermission(rs.getInt("permiso"));
        carpeta.setUsername(rs.getString("username"));
        carpeta.setPassword(rs.getString("password"));

        return carpeta;
    }
}
