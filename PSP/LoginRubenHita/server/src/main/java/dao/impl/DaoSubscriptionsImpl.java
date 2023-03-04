package dao.impl;

import dao.DBConnectionPool;
import dao.DaoSubscriptions;
import domain.model.Subscription;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoSubscriptionsImpl implements DaoSubscriptions {
    private ResultSet rs;
    private final DBConnectionPool pool;

    @Inject
    DaoSubscriptionsImpl(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<List<Subscription>, String> getAll(int idReader) {
        Either<List<Subscription>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.SELECT_SUBSCRIPTIONS);

            preparedStatement.setInt(1, idReader);

            rs = preparedStatement.executeQuery();

            List<Subscription> subscriptionList = new ArrayList<>();

            while (rs.next()) {
                int idNewspaper = rs.getInt("id_newspaper");
                int idReader1 = rs.getInt("id_reader");
                Date startDate = rs.getDate("start_date");
                Date cancellationDate = rs.getDate("cancellation_date");

                Subscription s = new Subscription(idNewspaper, idReader1, startDate, cancellationDate);
                subscriptionList.add(s);
            }

            response = Either.left(subscriptionList);

            pool.closeConnection(myConnection);
        } catch (SQLException e) {
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public Subscription save(Subscription s){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.INSERT_SUBSCRIPTION, s.getIdNewspaper(), s.getIdReader(), s.getStartDate(), s.getCancellationDate());

        return s;
    }

    public Subscription delete(Subscription s){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
        jdbcTemplate.update(SQLQueries.DELETE_SUBSCRIPTION, s.getIdNewspaper(), s.getIdReader(), s.getStartDate(), s.getCancellationDate());

        return s;
    }
}
