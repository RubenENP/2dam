package dao.impl;

import dao.DBConnectionPool;
import dao.DaoQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Query;
import utils.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoQueriesImpl implements DaoQueries {
    private ResultSet rs;
    private final DBConnectionPool pool;

    @Inject
    DaoQueriesImpl (DBConnectionPool pool){
        this.pool = pool;
    }

    public Either<List<Query>, String> query1() {
        Either<List<Query>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            rs = statement.executeQuery(SQLQueries.firstQuery);

            List<Query> queryList = new ArrayList<>();

            while (rs.next()) {
                String description = rs.getString("description");

                int numReader = rs.getInt("numReader");

                String name_article = rs.getString("name_article");

                queryList.add(new Query(description, numReader, name_article));
            }

            response = Either.left(queryList);

        } catch (SQLException e) {
            response = Either.right(e.getMessage());
        }

        return response;
    }
}
