module cliente {
    requires retrofit2;
    requires domain;
    requires lombok;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires jakarta.cdi;
    requires io.reactivex.rxjava3;
    requires io.vavr;
    requires okhttp3;
    requires moshi;
    requires retrofit2.converter.moshi;
    requires retrofit2.adapter.rxjava3;
    requires jakarta.inject;
    requires org.apache.logging.log4j;
    requires retrofit2.converter.gson;
    requires com.azure.core.serializer.json.gson;
    requires retrofit2.converter.scalars;


    exports ui.main to javafx.graphics;
    exports dao.impl;
    opens ui.main;
    exports dao.retrofit;
    exports ui.principal;
    opens ui.common;
    exports dao.retrofit.llamadas;
    opens dao.retrofit.llamadas;
    exports ui.common;

    opens fxml;

    exports servicios;
    exports servicios.impl;

    exports dao;

    opens dao;
    opens dao.impl;
    opens servicios;
    opens servicios.impl;

    opens ui.pantallas.newspapers;
    exports ui.menu;

    exports dao.gson;
    opens ui.menu to javafx.fxml;

    opens ui.pantallas.articles;

    opens ui.pantallas.readers;

    exports common;

    exports ui.pantallas.login;
    opens ui.pantallas.login;

    exports network;
}