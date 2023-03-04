package dao.impl;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import dao.ReaderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import model.Reader;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {
    private final MongoCollection<Document> collection;
    private final Gson gson;

    @Inject
    public ReaderDaoImpl(Gson gson) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase database = mongoClient.getDatabase("RubenHita");
        collection = database.getCollection("newspapers", Document.class).withCodecRegistry(pojoCodecRegistry);

    }

    @Override
    public Either<String, List<Reader>> getAll() {
        Either<String, List<Reader>> response;
        try {
            List<Reader> readerList = new ArrayList<>();
            for (Document document : collection.find()) {
                readerList.addAll(gson.fromJson(document.toJson(), Newspaper.class).getReaders());
            }
            response = Either.right(readerList);
        } catch (Exception e) {
          response  =Either.left(e.getMessage());
        }
        return response;
    }

    @Override
    public Either<String, List<Reader>> getAll(Newspaper newspaper) {
        Either<String, List<Reader>> response;
        try {
            List<Reader> readerList = new ArrayList<>();
            for (Document document : collection.find(Filters.eq("_id", newspaper.get_id()))) {
                readerList.addAll(gson.fromJson(document.toJson(), Newspaper.class).getReaders());
            }
            response = Either.right(readerList);
        } catch (Exception e) {
            response = Either.left(e.getMessage());
        }
        return response;
    }

    public int save(Reader reader, Newspaper newspaper){
        int response;

        try{
            Document document = collection.find(Filters.eq("_id", newspaper.get_id())).first();

            if (document != null){
                Document insertReader = new Document("id", reader.get_id())
                        .append("name", reader.getName())
                        .append("cancellationDate", reader.getCancellationDate());

                Document update = new Document("$push", new Document("readers", insertReader));
                Document.parse(gson.toJson(reader));
                collection.updateOne(document, update);

                response = 0;
            } else {
                response = 404;
            }

        } catch (Exception e){
            response = e.hashCode();
        }

        return response;
    }

    @Override
    public int update(Reader reader, Newspaper newspaper ,Integer id) {
        Document document = collection.find(Filters.eq("_id", newspaper.get_id())).first();
        if (document == null) {
            return 404;
        } else {
            Document result = new Document("id", id)
                    .append("name", reader.getName())
                    .append("cancellationDate", reader.getCancellationDate());
            Document update = new Document("$set", new Document("readers.$[elem]", result));
            Document.parse(gson.toJson(reader));
            collection.updateOne(document, update, new UpdateOptions().arrayFilters(List.of(Filters.eq("elem.id", id))));
            return 0;
        }
    }
}
