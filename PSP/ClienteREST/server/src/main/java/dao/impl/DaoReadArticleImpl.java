package dao.impl;

import dao.DBConnectionPool;
import dao.DaoReadArticle;
import domain.model.readArticle.ReadArticle;
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
public class DaoReadArticleImpl implements DaoReadArticle {

    private ResultSet rs;
    private final DBConnectionPool pool;

    @Inject
    DaoReadArticleImpl (DBConnectionPool pool){
        this.pool = pool;
    }

    public Either<List<ReadArticle>, String> getAll() {
        Either<List<ReadArticle>, String> response;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)){

            rs = statement.executeQuery(SQLQueries.SELECT_READARTICLES);

            List<ReadArticle> readArticleList = new ArrayList<>();

            while (rs.next()){
                int id = rs.getInt("id");
                int idArticle = rs.getInt("id_article");
                int idReader = rs.getInt("id_reader");
                int ranking = rs.getInt("ranking");

                ReadArticle readArticle = new ReadArticle(id, idArticle, idReader, ranking);
                readArticleList.add(readArticle);
            }

            response = Either.left(readArticleList);

            pool.closeConnection(myConnection);

        } catch (SQLException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public int save(ReadArticle readArticle) {
        int rowsAffected=0;

        try (Connection myConnection = pool.getConnection();
             Statement statement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)){

            rs = statement.executeQuery(SQLQueries.SELECT_READARTICLES);

            rs.moveToInsertRow();

            rs.updateInt(2, readArticle.getIdArticle());

            rs.updateInt(3, readArticle.getIdReader());

            rs.updateInt(4, readArticle.getRanking());

            rs.insertRow();

            rowsAffected = 0;

            pool.closeConnection(myConnection);
        } catch (SQLException e){
            System.err.format("\"SQL State: %s\\n%s\", e.getSQLState(), e.getMessage()");
        } catch (Exception e){
            e.printStackTrace();
        }

        return rowsAffected;
    }
}
