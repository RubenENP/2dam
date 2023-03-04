module server{
    requires domain;
    requires jakarta.jakartaee.web.api;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires io.vavr;
    requires spring.jdbc;
    requires lombok;
    requires org.apache.logging.log4j;
    requires jjwt.api;
    requires jjwt.impl;
    requires jjwt.gson;
    requires com.google.common;

    exports dao.impl;
    exports config;

    opens config;
    exports jakarta.security;
}