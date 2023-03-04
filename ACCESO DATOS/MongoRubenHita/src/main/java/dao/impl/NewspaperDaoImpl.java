package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.*;
import dao.NewspaperDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class NewspaperDaoImpl implements NewspaperDao {
    private final MongoCollection<Document> collection;

    private final Gson gson;

    @Inject
    public NewspaperDaoImpl(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase database = mongoClient.getDatabase("RubenHita");
        collection = database.getCollection("newspapers");
    }

    public Either<String, List<Newspaper>> getAll() {
        List<Newspaper> newspaperList = new ArrayList<>();
        Either<String, List<Newspaper>> response;

        try {
            List<Document> result = collection.find().into(new ArrayList<>());
            for (Document document : result) {
                ObjectId id = document.getObjectId("_id");
                String name = document.getString("name");
                String relDate = document.getString("relDate");
                Newspaper newspaper = new Newspaper(id, name, relDate);
                newspaperList.add(newspaper);
            }

            response = Either.right(newspaperList);
        } catch (Exception e) {
            response = Either.left(e.getMessage());
        }

        return response;
    }

    public int delete(Newspaper newspaper) {
        Document result = collection.find(eq("_id", newspaper.get_id())).first();
        if (result == null) {
            return 404;
        } else {
            if (!gson.fromJson(result.toJson(), Newspaper.class).getArticles().isEmpty()) {
                return 409;
            } else {
                collection.findOneAndDelete(eq("_id", newspaper.get_id()));
                return 0;
            }
        }
    }
}
