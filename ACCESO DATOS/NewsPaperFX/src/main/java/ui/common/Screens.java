package ui.common;

public enum Screens {
    LOGIN("/fxml/login.fxml"),
    MENU("/fxml/menu.fxml"), ADDNEWSPAPER("/fxml/newspaper/addNewspaper.fxml");
    private String ruta;
    Screens(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}
