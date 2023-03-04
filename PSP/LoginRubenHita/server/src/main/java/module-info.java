module server{
    requires lombok;
    requires org.yaml.snakeyaml;
    requires org.apache.logging.log4j;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires domain;
    requires io.vavr;
    requires spring.jdbc;
    requires jakarta.jakartaee.web.api;
    requires jakarta.mail;
    requires thymeleaf;
    requires jjwt.api;
    requires jjwt.impl;
    requires jjwt.gson;
}