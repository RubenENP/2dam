package ui.common;

public enum Pantallas {
    MENU("/fxml/menu.fxml"),
    USERMENU("/fxml/usermenu.fxml");
    private final String ruta;
    Pantallas(String ruta){this.ruta=ruta;}
    public String getRuta(){return ruta;}
}
