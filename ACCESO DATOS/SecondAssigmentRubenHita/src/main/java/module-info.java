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
    requires io.vavr;
    requires java.sql;
    requires commons.dbcp2;
    requires jakarta.annotation;
    requires com.zaxxer.hikari;
    requires spring.core;
    requires spring.jdbc;

    exports ui.main;
    opens ui.main;

    exports ui.common;
    opens ui.common;

    exports dao.impl;
    opens dao.impl;

    exports dao;
    opens dao;

    exports model.reader;
    opens model.reader;

    exports ui.screen.subscribe;
    opens ui.screen.subscribe;

    exports model.readArticle;
    opens model.readArticle;

    exports model;
    opens model;

    exports model.mapper;
    opens model.mapper;

    exports services.impl;
    opens services.impl;

    exports ui.screen.login;
    opens ui.screen.login;

    exports ui.screen.principal;

    exports config;
    opens config;

    exports ui.screen.menu;
    opens ui.screen.menu;

    exports ui.screen.readers;
    opens ui.screen.readers;

    exports ui.screen.articles;
    opens ui.screen.articles;

    opens ui.screen.newspapers;
    exports ui.screen.newspapers;

    opens ui.screen.readArticle;
    exports ui.screen.readArticle;

    opens ui.screen.principal;

    exports utils;
    opens utils;
}
