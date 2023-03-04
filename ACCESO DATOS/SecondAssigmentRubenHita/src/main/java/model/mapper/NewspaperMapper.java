package model.mapper;

import model.Article;
import model.Newspaper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewspaperMapper implements RowMapper<Newspaper> {
    @Override
    public Newspaper mapRow(ResultSet rs, int rowNum) throws SQLException {
        Newspaper n = new Newspaper();

        n.setId(rs.getInt("id"));
        n.setName_newspaper(rs.getString("name_newspaper"));
        n.setRelease_date(rs.getDate("release_date"));

        return n;
    }
}
