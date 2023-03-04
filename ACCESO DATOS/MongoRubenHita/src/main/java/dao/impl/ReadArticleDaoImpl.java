package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dao.ReadArticleDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.Newspaper;
import model.ReadArticle;
import org.bson.Document;

import java.util.List;

public class ReadArticleDaoImpl implements ReadArticleDao {
    private final MongoCollection<Document> collection;

    private final Gson gson;

    @Inject
    public ReadArticleDaoImpl(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase database = mongoClient.getDatabase("RubenHita");
        collection = database.getCollection("newspapers");
    }
    @Override
    public Either<String, ReadArticle> add(ReadArticle readArticles, Newspaper newspaper, Article article) {
        Document newspaperDocument = collection.find(Filters.eq("_id", newspaper.get_id())).first();
        if (newspaperDocument == null) {
            return Either.left("newspaper dont exist");
        } else {
            List<Document> articleList = newspaperDocument.get("articles", List.class);
            Document articleDocument = articleList.stream()
                    .filter(a -> a.getString("desc").equals(article.getDesc()))
                    .findFirst()
                    .orElse(null);
            if (articleDocument == null) {
                return Either.left("article dont exist");
            } else {
                List<Document> readArticlesList = articleDocument.get("readArticles", List.class);
                readArticlesList.add(new Document("id", readArticles.getId())
                        .append("rating", readArticles.getRating()));

                articleDocument.append("readArticles", readArticlesList);
                collection.updateOne(Filters.eq("_id", newspaper.get_id()), new Document("$set", new Document("articles", articleList)));
                return Either.right(readArticles);
            }
        }
    }
}
