package dao.impl;

import dao.DBConnectionPool;
import dao.DaoReaders;
import domain.model.Llamada;
import domain.model.Usuario;
import domain.model.mapper.LlamadasMapper;
import domain.model.mapper.ReaderMapper;
import domain.model.reader.Reader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import utils.SQLQueries;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReadersImpl implements DaoReaders {
    private ResultSet rs;
    private final DBConnectionPool pool;

    @Inject
    DaoReadersImpl(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<List<Reader>, String> getAll() {
        Either<List<Reader>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = statement.executeQuery(SQLQueries.SELECT_readers_QUERY);

            List<Reader> readerList = new ArrayList<>();

            while (rs.next()) {
                String date = rs.getString("birth_reader");

                LocalDate localDate = LocalDate.parse(date);

                Reader r = new Reader(Integer.parseInt(rs.getString("id"))
                        , rs.getString("name_reader")
                        , localDate);
                readerList.add(r);
            }

            response = Either.left(readerList);

            pool.closeConnection(myConnection);
        } catch (SQLException e) {
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public Either<String, Reader> get(int id, String username){
        Either<String, Reader> response;

        if (comprobarLlamadas(username) == 0){
            JdbcTemplate jdbcTemplate = new JdbcTemplate(pool.getDataSource());
            Reader reader = jdbcTemplate.query("SELECT * FROM reader WHERE id=?", new ReaderMapper(), id)
                    .stream().findFirst().orElse(null);

            if (reader == null){
                response = Either.left("No existe ese reader");
            } else {
                response = Either.right(reader);
            }
        } else {
            response = Either.left("Te pasaste de las 10 llamadas, prueba mas tarde");
        }

        return response;
    }

    public Either<String, Reader> save(Reader reader, String username) {
        Either<String, Reader> response;
        int rowsAffected = 0;

        if (comprobarLlamadas(username) == 0){

            try (Connection myConnection = pool.getConnection();
                 Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                         ResultSet.CONCUR_UPDATABLE)) {

                rs = statement.executeQuery(SQLQueries.SELECT_readers_QUERY);

                rs.moveToInsertRow();

                rs.updateInt(1, reader.getId());
                rs.updateString(2, reader.getName());

                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(reader.getBirthDate().toString());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                rs.updateDate(3, sqlDate);

                rs.insertRow();

                response = Either.right(reader);

                pool.closeConnection(myConnection);
            } catch (SQLException e) {
                System.err.format("\"SQL State: %s\\n%s\", e.getSQLState(), e.getMessage()");
                response = Either.left(e.getMessage());
            } catch (Exception e) {
                response = Either.left(e.getMessage());
            }
        } else {
            response = Either.left("Has hecho mas de 10 llamadas, intentalo mas tarde");
        }

        return response;
    }

    public int delete(Reader reader) {
        int rowsAffected = 0;

        try (Connection myConnection = pool.getConnection();
             //Delete reader
             PreparedStatement preparedStatementDeleteReader = myConnection.prepareStatement(SQLQueries.DELETE_READER)) {

            preparedStatementDeleteReader.setInt(1, reader.getId());

            //Delete subcriptions
            PreparedStatement preparedStatementDeleteSubscriptions = myConnection.prepareStatement(SQLQueries.DELETE_SUBSCRIPTIONS);

            preparedStatementDeleteSubscriptions.setInt(1, reader.getId());

            //Delete read articles
            PreparedStatement preparedStatementDeleteReadArticles = myConnection.prepareStatement(SQLQueries.DELETE_READ_ARTCILES);

            preparedStatementDeleteReadArticles.setInt(1, reader.getId());

            rowsAffected = preparedStatementDeleteSubscriptions.executeUpdate()
                    + preparedStatementDeleteReadArticles.executeUpdate() + preparedStatementDeleteReader.executeUpdate();

            pool.closeConnection(myConnection);
        } catch (SQLException e) {
            Logger.getLogger(DaoReadersImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public int update(Reader reader) {
        int rowsAffected = 0;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {

            myConnection.setAutoCommit(false);

            rs = statement.executeQuery(SQLQueries.SELECT_readers_QUERY);

            PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.UPDATE_READER);

            preparedStatement.setString(1, reader.getName());

            preparedStatement.setInt(2, reader.getId());

            rowsAffected = preparedStatement.executeUpdate();

            myConnection.commit();

            pool.closePool();
        } catch (SQLException e) {
            Logger.getLogger(DaoReadersImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    private void updateLlamada(String username) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        Llamada llamada = jtm.query("SELECT * from llamadas WHERE user = ?", new LlamadasMapper(), username)
                .stream().findFirst().orElse(null);

        jtm.update("UPDATE llamadas SET cantidad = ? WHERE user=?",
                llamada.getCantidad() + 1, llamada.getUser());
    }

    private int comprobarLlamadas(String username) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        Llamada llamada = jtm.query("SELECT * from llamadas WHERE user = ?", new LlamadasMapper(), username)
                .stream().findFirst().orElse(null);

        int response = 0;

        if (llamada == null) {
            jtm.update("INSERT INTO llamadas (user, fecha, cantidad) VALUES(?,?,?)", username,
                    LocalDateTime.now(), 1);
        } else {
            if (llamada.getFecha().plusMinutes(5).isBefore(LocalDateTime.now())) {
                jtm.update("UPDATE llamadas SET fecha=?, cantidad = 0 WHERE user=?",
                        LocalDateTime.now(), llamada.getUser());
            } else if (llamada.getCantidad() >= 10) {
                response = 1;
            } else {
                updateLlamada(username);
            }
        }

        return response;
    }
}
