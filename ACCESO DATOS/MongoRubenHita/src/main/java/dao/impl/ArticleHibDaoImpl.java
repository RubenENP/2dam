package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dao.ArticleHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.Newspaper;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ArticleHibDaoImpl implements ArticleHibDao {
    private final MongoCollection<Document> collection;

    private final Gson gson;

    @Inject
    public ArticleHibDaoImpl(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase database = mongoClient.getDatabase("RubenHita");
        collection = database.getCollection("newspapers");
    }

    public int delete(Newspaper newspaper) {
        Document document = collection.find(Filters.eq("_id", newspaper.get_id())).first();
        if (document == null) {
            return 404;
        }else {
            Document update = new Document("$set", new Document("articles", new ArrayList<>()));
            collection.updateOne(document, update);
            return 200;
        }

    }

    @Override
    public Either<String, List<Article>> getAll(Newspaper newspaper) {
        try {
            List<Article> articleList = new ArrayList<>();
            for (Document document : collection.find(Filters.eq("_id", newspaper.get_id()))) {
                articleList.addAll(gson.fromJson(document.toJson(), Newspaper.class).getArticles());
            }
            return Either.right(articleList);

        } catch (Exception e) {
            return Either.left(e.getMessage());
        }
    }
}
