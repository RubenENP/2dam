package dao.impl;

import config.Configuration;
import dao.DBConnectionPool;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.SQLQueries;

public class DaoWeaponsImpl {
    DBConnectionPool dbConnectionPool = new DBConnectionPool(new Configuration());

    public int delete(int id){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnectionPool.getDataSource());
        return jdbcTemplate.update(SQLQueries.DELETE_FROM_WEAPONS, id);
    }
}
