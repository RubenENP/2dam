module JuegoSingaJavaFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires lombok;
    requires org.apache.logging.log4j;

    exports ui.main;
    exports nightmare;
    exports nightmare.pnj;
    exports nightmare.spells;
    exports nightmare.util;
    exports nightmare.dungeon;
    exports nightmare.pj;
    exports nightmare.items;
    exports nightmare.management;
    exports ui.common;
    exports ui.pantalla.principal;

    opens ui.main;
    exports ui.pantalla.menu;
    opens ui.pantalla.menu;
    opens nightmare.pnj;
    opens nightmare.spells;
    opens nightmare.util;
    opens nightmare.dungeon;
    opens nightmare.pj;
    opens nightmare.items;
    opens nightmare.management;
    opens ui.common;
    opens ui.pantalla.principal;

}