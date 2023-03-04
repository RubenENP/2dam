package domain.model.mapper;

import domain.model.reader.Reader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMapper implements RowMapper<Reader> {
    @Override
    public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reader r = new Reader();
        r.setId(rs.getInt("id"));
        r.setName(rs.getString("name_reader"));
        r.setBirthDate(rs.getDate("birth_reader").toLocalDate());
        return r;
    }
}
