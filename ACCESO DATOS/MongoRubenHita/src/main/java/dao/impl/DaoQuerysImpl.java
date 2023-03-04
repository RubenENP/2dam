package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.*;
import dao.DaoQuerys;
import jakarta.inject.Inject;
import org.bson.BsonNull;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DaoQuerysImpl implements DaoQuerys {
    private final MongoCollection<Document> collection;
    private final MongoDatabase database;

    private final Gson gson;

    @Inject
    public DaoQuerysImpl(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        database = mongoClient.getDatabase("RubenHita");
        collection = database.getCollection("newspapers");
    }

    public List<Integer> getReadersIdOfAType(String type) {
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$match",
                        new Document("articles.type", "Sports")),
                new Document("$group",
                        new Document("_id", "null")
                                .append("id",
                                        new Document("$push", "$articles.readArticles.id")))));

        return result.first().getList("id", Integer.class);
    }

    public int avgOfArticlesRateByAReader(String name) {

        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$match",
                        new Document("readers.name", name)),
                new Document("$sort",
                        new Document("name", 1L)),
                new Document("$group",
                        new Document("_id", "$articles.readArticles.id")
                                .append("avg_val",
                                        new Document("$avg", "$articles.readArticles.rating"))));

        AggregateIterable<Document> result = collection.aggregate(i);
        int r = 0;
        for (Document document : result) {
            r += document.getDouble("avg_val");
        }

        return r;
    }

    public String typeOfArticlesMoreRead() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$group",
                        new Document("_id", "$articles.type")
                                .append("count",
                                        new Document("$sum", 1L))),
                new Document("$sort",
                        new Document("count", -1L)),
                new Document("$limit", 1L));

        AggregateIterable<Document> result = collection.aggregate(i);
        String r = "";
        for (Document document : result) {
            r = document.getString("_id");
        }

        return r;
    }

    public List<Integer> numberOfArticlesOfEachTypeOfaNewspaper(String name) {
        List<Document> i = Arrays.asList(new Document("$match",
                        new Document("name", name)),
                new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$group",
                        new Document("_id", "$articles.type")
                                .append("count",
                                        new Document("$sum", 1))));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<Integer> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.getInteger("count"));
        }

        return r;
    }

    public List<String> getArticlesDescriptionAndNumberOfReaders() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$project",
                        new Document("description", "$articles.desc")
                                .append("readers",
                                        new Document("$size", "$articles.readArticles"))));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }

    public List<String> nameOfNewspaperOrderByOld() {
        List<Document> i = Arrays.asList(new Document("$sort",
                        new Document("relDate", 1L)),
                new Document("$project",
                        new Document("name", "$name")),
                new Document("$limit", 10L));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.get("name").toString());
        }

        return r;
    }

    public List<String> getArticlesOfAType(String type) {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$match",
                        new Document("articles.type", type)),
                new Document("$project",
                        new Document("name", "$name")
                                .append("articles", "$articles")));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }

    public List<String> getNumberOfSportsArticlesByNewspaper() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$match",
                        new Document("articles.type", "Sports")),
                new Document("$group",
                        new Document("_id", "$name")
                                .append("articles",
                                        new Document("$sum", 1L))));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }

    public String nameOfNewspaperWithHighestNumberOfSportsArticles() {
        List<Document> i = Arrays.asList(new Document("$match",
                        new Document("articles.type", "Sports")),
                new Document("$sort",
                        new Document("articles", 1L)),
                new Document("$limit", 1L),
                new Document("$project",
                        new Document("name", "$name")));

        AggregateIterable<Document> result = collection.aggregate(i);

        return Objects.requireNonNull(result.first()).getString("name");
    }

    public List<String> getArticlesWithNoRatingLowerThan3() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$match",
                        new Document("articles.readArticles.rating",
                                new Document("$gte", 3L))),
                new Document("$project",
                        new Document("name", "$articles.title")));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.getString("name"));
        }

        return r;
    }

    public double avgReadArticlesPerReader() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$group",
                        new Document("_id", "$articles.readArticles.id")
                                .append("subscriptions",
                                        new Document("$sum", 1L))),
                new Document("$group",
                        new Document("_id",
                                "$readers.name")
                                .append("avg",
                                        new Document("$avg", "$subscriptions"))));

        AggregateIterable<Document> result = collection.aggregate(i);
        double r = 0;
        for (Document document : result) {
            r = document.getDouble("avg");
        }

        return r;
    }


    public List<String> getNumberOfReadArticlesPerReader() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$group",
                        new Document("_id", "$articles.readArticles.id")
                                .append("subscriptions",
                                        new Document("$sum", 1L))));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }

    public int articlesWithAvgRatingGreaterThan4() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$group",
                        new Document("_id", "$articles")
                                .append("avg",
                                        new Document("$avg", "$articles.readArticles.rating"))),
                new Document("$match",
                        new Document("avg",
                                new Document("$gte", 4L))),
                new Document("$group",
                        new Document("_id", "null")
                                .append("num",
                                        new Document("$sum", 1))));

        AggregateIterable<Document> result = collection.aggregate(i);

        return Objects.requireNonNull(result.first()).getInteger("num");
    }

    public List<String> getReadersWithNoReviewLowerThan3() {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$match",
                        new Document("articles.readArticles.rating",
                                new Document("$gte", 3L))),
                new Document("$project",
                        new Document("readers", 1L)));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }

    public List<String> getArticlesWithRatingLowerThan5OfNewspaper(String name) {
        List<Document> i = Arrays.asList(new Document("$unwind",
                        new Document("path", "$articles")),
                new Document("$unwind",
                        new Document("path", "$articles.readArticles")),
                new Document("$match",
                        new Document("name", name)),
                new Document("$match",
                        new Document("articles.readArticles.rating",
                                new Document("$lt", 5L))));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }

    public List<String> getReadersNotRegisteredAsUsers() {
        List<Document> i = Arrays.asList(new Document("$lookup",
                        new Document("from", "login")
                                .append("localField", "readers.name")
                                .append("foreignField", "user")
                                .append("as", "result")),
                new Document("$unwind",
                        new Document("path", "$readers")),
                new Document("$unwind",
                        new Document("path", "$result")),
                new Document("$group",
                        new Document("_id", "$readers.name")
                                .append("name",
                                        new Document("$push", "$result.user"))));

        AggregateIterable<Document> result = collection.aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            String name = document.getString("_id");
            if (!Objects.equals(name, document.getList("name", String.class).get(0))) {
                r.add(document.getString("_id"));
            }
        }

        return r;
    }

    public List<String> getCarModelsOfPeopleOver35Years() {
        List<Document> i = Arrays.asList(new Document("$match",
                        new Document("age",
                                new Document("$gte", 35L))),
                new Document("$unwind",
                        new Document("path", "$cars")),
                new Document("$project",
                        new Document("cars.model", 1L)));

        AggregateIterable<Document> result = database.getCollection("person").aggregate(i);
        List<String> r = new ArrayList<>();
        for (Document document : result) {
            r.add(document.toJson());
        }

        return r;
    }
}
