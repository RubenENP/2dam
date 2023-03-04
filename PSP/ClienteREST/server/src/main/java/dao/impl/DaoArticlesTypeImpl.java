package dao.impl;

import dao.DBConnectionPool;
import dao.DaoArticlesType;
import domain.model.ArticleType;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import utils.SQLQueries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoArticlesTypeImpl implements DaoArticlesType {
    private ResultSet rs;
    private final DBConnectionPool pool;
    @Inject
    DaoArticlesTypeImpl(DBConnectionPool pool){
        this.pool = pool;
    }
    public Either<List<ArticleType>, String> getAll(){
        Either<List<ArticleType>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)){

            rs = statement.executeQuery(SQLQueries.SELECT_ARTICLETYPES);


            List<ArticleType> articleTypeList = new ArrayList<>();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("description");

                ArticleType articleType = new ArticleType(id, name);

                articleTypeList.add(articleType);
            }

            response = Either.left(articleTypeList);

            pool.closeConnection(myConnection);
        } catch (SQLException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }
}
