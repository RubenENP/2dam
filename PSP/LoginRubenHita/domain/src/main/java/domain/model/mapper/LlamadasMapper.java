package domain.model.mapper;

import domain.model.Llamada;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LlamadasMapper implements RowMapper<Llamada> {
    @Override
    public Llamada mapRow(ResultSet rs, int rowNum) throws SQLException {
        Llamada l = new Llamada();
        l.setUser(rs.getString("user"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        l.setFecha(LocalDateTime.parse(rs.getDate("fecha") + " " + rs.getTime("fecha").toString(), format));
        l.setCantidad(rs.getInt("cantidad"));
        return l;
    }
}
