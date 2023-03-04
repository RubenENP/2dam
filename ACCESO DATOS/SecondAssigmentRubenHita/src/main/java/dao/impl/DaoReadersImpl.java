package dao.impl;

import dao.DBConnection;
import dao.DBConnectionPool;
import dao.DaoReaders;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.reader.Reader;
import utils.SQLQueries;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReadersImpl implements DaoReaders {
    private ResultSet rs;
    private final DBConnectionPool pool;

    @Inject
    DaoReadersImpl (DBConnectionPool pool){
        this.pool = pool;
    }

    public Either<List<Reader>, String> getAll() {
        Either<List<Reader>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)){

            ResultSet rs = statement.executeQuery(SQLQueries.SELECT_readers_QUERY);

            List<Reader> readerList = new ArrayList<>();

            while (rs.next()){
                String date = rs.getString("birth_reader");

                LocalDate localDate = LocalDate.parse(date);

                Reader r = new Reader(Integer.parseInt(rs.getString("id"))
                        , rs.getString("name_reader")
                        , localDate);
                readerList.add(r);
            }

            response = Either.left(readerList);

            pool.closeConnection(myConnection);
        } catch (SQLException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public Either<List<Reader>, String> getAllByNewspaperId(int id){
        Either<List<Reader>, String> response;

        try (Connection myConnection = pool.getConnection();
            Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {

            PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.SELECT_READERS_BY_NEWSPAPER_ID);

            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            List<Reader> readerList = new ArrayList<>();

            while (rs.next()){
                String date = rs.getString("birth_reader");

                LocalDate localDate = LocalDate.parse(date);

                Reader r = new Reader(Integer.parseInt(rs.getString("id"))
                        , rs.getString("name_reader")
                        , localDate);
                readerList.add(r);
            }

            response = Either.left(readerList);

        } catch (SQLException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public Either<List<Reader>, String> getAllByArticleTypeId(int id){
        Either<List<Reader>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            PreparedStatement preparedStatement = myConnection.prepareStatement(SQLQueries.SELECT_READERS_BY_ARTICLETYPE_ID);

            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            List<Reader> readerList = new ArrayList<>();

            while (rs.next()){
                String date = rs.getString("birth_reader");

                LocalDate localDate = LocalDate.parse(date);

                Reader r = new Reader(Integer.parseInt(rs.getString("id"))
                        , rs.getString("name_reader")
                        , localDate);
                readerList.add(r);
            }

            response = Either.left(readerList);

        } catch (SQLException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public int save(Reader reader){
        int rowsAffected=0;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)){

            rs = statement.executeQuery(SQLQueries.SELECT_readers_QUERY);

            rs.moveToInsertRow();

            rs.updateInt(1, reader.getId());
            rs.updateString(2, reader.getName());

            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(reader.getBirthDate().toString());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            rs.updateDate(3, sqlDate);

            rs.insertRow();

            rowsAffected = 1;

            pool.closeConnection(myConnection);
        } catch (SQLException e){
            System.err.format("\"SQL State: %s\\n%s\", e.getSQLState(), e.getMessage()");
        } catch (Exception e){
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public int delete(Reader reader) {
        int rowsAffected=0;

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
        }catch (SQLException e){
            Logger.getLogger(DaoReadersImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e){
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public int update(Reader reader){
        int rowsAffected=0;

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
        } catch (SQLException e){
            Logger.getLogger(DaoReadersImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e){
            e.printStackTrace();
        }

        return rowsAffected;
    }

    // Private methods
    private void readRS(ResultSet rs) {
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameReader = rs.getString("name_reader");
                Date birthReader = rs.getDate("birth_reader");
                System.out.println(id + ", " + nameReader + ", " + birthReader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
