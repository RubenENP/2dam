module domain {
    exports domain.model;
    exports domain.model.readArticle;
    exports domain.model.reader;
    exports domain.model.mapper;
    exports domain.errores;

    requires lombok;
    requires java.sql;
    requires spring.jdbc;

    opens domain.model;
    opens domain.model.reader;

    opens domain.errores;
}