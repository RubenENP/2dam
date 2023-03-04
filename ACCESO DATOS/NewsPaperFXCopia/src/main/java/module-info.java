module javafx
{
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires lombok;
    requires org.apache.logging.log4j;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.google.gson;
    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires moshi;
    requires okhttp3;
    requires jakarta.xml.bind;

    exports ui.main;
    opens ui.main;

    exports ui.common;
    opens ui.common;

    exports ui.screens.login;
    opens ui.screens.login;

    exports ui.screens.principal;
    opens ui.screens.principal;

    exports ui.screens.menu;
    opens ui.screens.menu;

    exports config;
    opens config;

    exports dao.impl;
    opens dao.impl;

    exports dao;
    opens dao;

    exports model;
    opens model;

    exports ui.screens.newspapers;
    opens ui.screens.newspapers;

    exports services.impl;
    opens services.impl;

    exports ui.screens.artciles;
    opens ui.screens.artciles;
    exports model.reader;
    opens model.reader;

    exports model.subscription;
    opens model.subscription;

    exports model.readArticle;
    opens model.readArticle;

    exports ui.screens.readers;
    opens ui.screens.readers;
}
