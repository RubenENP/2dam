package dao.impl;

import dao.DBConnectionPool;
import dao.DaoNewspapers;
import domain.model.Newspaper;
import domain.model.mapper.NewspaperMapper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.SQLQueries;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

@Log4j2
public class DaoNewspapersImpl implements DaoNewspapers {
    private ResultSet rs;
    private final DBConnectionPool pool;

    @Inject
    DaoNewspapersImpl (DBConnectionPool pool){
        this.pool = pool;
    }

    public List<Newspaper> getAll() {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<Newspaper> list = jtm.query(SQLQueries.SELECT_NEWSPAPERS, new NewspaperMapper());

        return list;
    }

    public Newspaper addNewspaper(Newspaper n){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                pool.getDataSource()
        ).withTableName("newspaper")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name_newspaper", n.getName_newspaper());
        parameters.put("release_date", n.getRelease_date());

        n.setId((int) jdbcInsert.executeAndReturnKey(parameters).longValue());

        return n;
    }

    public Newspaper delete(Newspaper n){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.DELETE_READARTICLES_WITH_NEWSPAPER, n.getId());
        jdbcTemplate.update(SQLQueries.DELETE_ARTICLE_WITHNEWSPAPER, n.getId());
        jdbcTemplate.update(SQLQueries.DELETE_SUBSCRIPTION_WITH_NEWSPAPER, n.getId());
        jdbcTemplate.update(SQLQueries.DELETE_NEWSPAPER, n.getId());

        return n;
    }
}
