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

}
