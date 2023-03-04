package model.mapper;

import model.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article a = new Article();
        a.setId(rs.getInt("id"));
        a.setName_article(rs.getString("name_article"));
        a.setDescription(rs.getString("description"));
        a.setId_type(rs.getInt("id_type"));
        a.setId_newspaper(rs.getInt("id_newspaper"));

        return a;
    }
}
