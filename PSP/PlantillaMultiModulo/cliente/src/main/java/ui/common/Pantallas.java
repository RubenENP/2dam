package ui.common;

public enum Pantallas {
    MENU("/fxml/menu.fxml"),
    NEWSPAPERS("/fxml/newspapers.fxml"),
    ARTICLES("/fxml/articles.fxml"),
    READERS("/fxml/readers.fxml");
    private final String ruta;
    Pantallas(String ruta){this.ruta=ruta;}
    public String getRuta(){return ruta;}
}
