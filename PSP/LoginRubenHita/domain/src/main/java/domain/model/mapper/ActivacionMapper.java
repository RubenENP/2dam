package domain.model.mapper;

import domain.model.Activacion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivacionMapper implements RowMapper<Activacion> {
    @Override
    public Activacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Activacion activacion = new Activacion();
        activacion.setCod(rs.getString("cod"));
        activacion.setTime(rs.getTime("time"));
        activacion.setUser(rs.getString("user"));

        return activacion;
    }
}
