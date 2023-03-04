module javafx {
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
    requires org.apache.logging.log4j;
    requires retrofit2.converter.gson;
    requires retrofit2.converter.scalars;
    requires jakarta.annotation;
    requires jakarta.inject;
    requires com.azure.core.serializer.json.gson;

    exports ui.main;
    exports dao.impl;
    opens ui.main;
    opens ui.common;
    exports ui.common;
    opens fxml;
    exports dao;
    opens dao;
    opens dao.impl;
    exports common;
    exports dao.gson;

    exports retrofit.network;
    exports services.impl;
    exports retrofit;
    exports ui.screens.menu;
    exports ui.screens.usermenu;
    exports services;
    exports ui.screens.adminmenu;
    opens ui.screens.principal;
    exports retrofit.llamadas;
    opens ui.screens.menu;
    opens ui.screens.adminmenu;
    opens ui.screens.usermenu;

    opens retrofit to com.google.gson;
    exports ui.screens.principal;
}