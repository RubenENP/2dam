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
    requires retrofit2;
    requires retrofit2.converter.moshi;
    requires moshi;
    requires okhttp3;
    requires okio;
    requires io.vavr;
    requires io.reactivex.rxjava3;
    requires retrofit2.adapter.rxjava3;
    requires org.pdfsam.rxjavafx;

    exports ui.main;
    opens ui.main;

    exports ui.common;
    opens ui.common;

    exports ui.pantallas.menu;
    opens ui.pantallas.menu;

    exports ui.pantallas.principal;
    opens ui.pantallas.principal;

    exports ui.pantallas.characters;
    opens ui.pantallas.characters;

    exports dao.impl;
    opens dao.impl;

    exports modelo.characters;
    opens modelo.characters;

    exports dao;
    opens dao;

    exports ui.pantallas.locations;
    opens ui.pantallas.locations;

    exports modelo.locations;
    opens modelo.locations;

    exports modelo.episodes;
    opens modelo.episodes;

    exports ui.pantallas.episodes;
    opens ui.pantallas.episodes;

    exports servicios.impl;
    opens servicios.impl;

    exports servicios;
    opens servicios;

    exports dao.retrofit;
    opens dao.retrofit;
}
