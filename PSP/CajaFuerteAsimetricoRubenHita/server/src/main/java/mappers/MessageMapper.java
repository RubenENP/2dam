package mappers;

import modelo.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message m = new Message();
        m.setId(rs.getInt("id"));
        m.setIdCarpeta(rs.getInt("id_carpeta"));
        m.setText(rs.getString("text"));
        return m;
    }
}
