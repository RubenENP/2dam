package utils;

public class SQLQueries {
    public static final String SELECT_readers_QUERY = "SELECT * from reader";
    public static final String INSERT_INTO_reader = "INSERT INTO reader(id, name_reader, birth_reader) VALUES (?, ?, ?)";
    public static final String DELETE_READER = "delete from reader where id = ?";
    public static final String DELETE_SUBSCRIPTIONS = "delete from subscribe where id_reader = ?";
    public static final String DELETE_SUBSCRIPTION = "delete from subscribe where id_reader = ?";
    public static final String DELETE_SUBSCRIPTION_WITH_NEWSPAPER = "delete from subscribe where id_newspaper = ?";
    public static final String DELETE_READ_ARTCILES = "delete from readarticle where id_reader = ?";
    public static final String UPDATE_READER = "update reader set name_reader = ? where id = ?";
    public static final String SELECT_NEWSPAPERS = "SELECT * from newspaper";
    public static final String SELECT_SUBSCRIPTIONS = "SELECT * from subscribe where id_reader = ?";
    public static final String SELECT_ARTICLETYPES = "SELECT * from type";
    public static final String SELECT_LOGIN = "SELECT * from login";
    public static final String SELECT_ARTICLES = "SELECT * from article";
    public static final String SELECT_ARTICLES_BY_TYPE = "SELECT * from article where article.id_type = ?;";
    public static final String SELECT_READARTICLES = "SELECT * from readarticle";
    public static final String SELECT_READERS_BY_NEWSPAPER_ID = "SELECT reader.id, name_reader, birth_reader from reader, subscribe where id = subscribe.id_reader AND subscribe.id_newspaper = ?";
    public static final String SELECT_READERS_BY_ARTICLETYPE_ID = "SELECT DISTINCT reader.id, name_reader, birth_reader from reader, subscribe, newspaper, article where reader.id = subscribe.id_reader AND subscribe.id_newspaper = newspaper.id AND article.id_newspaper = newspaper.id AND article.id_type = ?";

    public static final String SELECT_READER_NAME_BY_NEWSPAPER_NAME = "select reader.name_reader from reader, subscribe, newspaper where reader.id = subscribe.id_reader AND subscribe.id_newspaper = newspaper.id AND newspaper.name_newspaper = 'Tempo' limit 100";

    public static final String INSERT_NEWSPAPER = "INSERT INTO newspaper (name_newspaper, release_date) values (?, ?)";
    public static final String DELETE_NEWSPAPER = "DELETE from newspaper WHERE id = ?";
    public static final String DELETE_ARTICLE = "DELETE from article WHERE id = ?";
    public static final String DELETE_ARTICLE_WITHNEWSPAPER = "DELETE from article WHERE id_newspaper = ?";
    public static final String INSERT_ARTICLE = "INSERT INTO article (name_article, description, id_type, id_newspaper) values (?, ?, ?, ?)";
    public static final String SELECT_ARTICLES_RATING_LOWER_5 = "select distinct article.id, article.name_article, article.description, article.id_type, article.id_newspaper from article, newspaper, subscribe, readarticle, reader where article.id_newspaper = newspaper.id AND newspaper.id = subscribe.id_newspaper AND subscribe.id_reader = reader.id AND reader.id = readarticle.id_reader AND readarticle.ranking < 5;";
    public static final String INSERT_SUBSCRIPTION = "INSERT INTO subscribe (id_newspaper, id_reader, start_date, cancellation_date) values (?, ?, ?, ?)";
    public static final String DELETE_READARTICLES_WITH_NEWSPAPER = "DELETE from readarticle where id_article in (select id from article where id_newspaper = ?)";
}
