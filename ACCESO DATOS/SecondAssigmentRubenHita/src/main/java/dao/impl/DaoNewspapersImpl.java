package dao.impl;

import dao.DBConnectionPool;
import dao.DaoNewspaper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Newspaper;
import model.mapper.NewspaperMapper;
import utils.SQLQueries;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

@Log4j2
public class DaoNewspapersImpl implements DaoNewspaper {
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
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.INSERT_NEWSPAPER, n.getName_newspaper(), n.getRelease_date());
        /*

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                pool.getDataSource()
        ).withTableName("newspaper")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name_newspaper", n.getName_newspaper());
        parameters.put("release_date", n.getRelease_date());

        n.setId((int) jdbcInsert.executeAndReturnKey(parameters).longValue());*/

        return n;
    }

    public Newspaper delete(Newspaper n){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.DELETE_ARTICLE, n.getId());
        jdbcTemplate.update(SQLQueries.DELETE_NEWSPAPER, n.getId());

        return n;
    }
}
