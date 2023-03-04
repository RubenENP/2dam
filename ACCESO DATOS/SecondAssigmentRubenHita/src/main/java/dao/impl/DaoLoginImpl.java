package dao.impl;

import dao.DBConnectionPool;
import dao.DaoLogin;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Login;
import utils.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoLoginImpl implements DaoLogin {
    private ResultSet rs;
    private final DBConnectionPool pool;
    @Inject
    DaoLoginImpl (DBConnectionPool pool){
        this.pool = pool;
    }
    public Either<List<Login>, String> getAll(){
        Either<List<Login>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)){

            rs = statement.executeQuery(SQLQueries.SELECT_LOGIN);

            List<Login> loginList = new ArrayList<>();

            while (rs.next()) {
                String user = rs.getString("user");
                String password = rs.getString("password");
                int idReader = rs.getInt("id_reader");

                Login l = new Login(user, password, idReader);

                loginList.add(l);
            }

            response = Either.left(loginList);

            pool.closeConnection(myConnection);

        } catch (SQLException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }
}
