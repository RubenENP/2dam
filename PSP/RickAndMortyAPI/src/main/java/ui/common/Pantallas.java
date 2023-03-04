package ui.common;

public enum Pantallas {
    MENU("/fxml/menu.fxml"),
    PERSONAJES("/fxml/characters.fxml"),
    SITIOS("/fxml/locations.fxml"),
    EPISODIOS("/fxml/episodes.fxml");
    private final String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}
