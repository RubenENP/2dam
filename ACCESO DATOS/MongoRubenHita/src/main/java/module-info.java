module javafx{
    requires io.vavr;
    requires jakarta.inject;
    requires mongo.java.driver;
    requires lombok;
    requires java.sql;
    requires jakarta.xml.bind;
    requires jakarta.cdi;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires com.google.gson;

    exports ui.main;
    exports services.impl;

    opens services.impl;
    exports ui.screen.articles;
    exports dao.impl;
    opens ui.main;
    exports model;
    exports ui.screen.menu;
    opens ui.screen.newspapers;
    exports ui.screen.principal;
    exports ui.common;
    opens ui.screen.principal;
    opens ui.screen.menu;
    opens ui.screen.articles;

    exports dao;
    exports ui.screen.readers;
    exports common;

    opens model;
    exports ui.screen.readArticle;
}