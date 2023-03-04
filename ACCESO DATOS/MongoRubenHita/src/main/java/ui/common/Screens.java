package ui.common;

public enum Screens {
    MENU("/fxml/menu.fxml"),
    SHOWALLNEWSPAPERS("/fxml/newspaper/showNewspapers.fxml"),
    SHOWARTICLES("/fxml/articles/showArticles.fxml"),
    ADDREADERS("/fxml/readers/addReader.fxml"),
    UPDATEREADERS("/fxml/readers/updateReader.fxml"),
    DELETENEWSPAPER("/fxml/newspaper/deleteNewspaper.fxml"),
    DELETEREADER("/fxml/readers/deleteReader.fxml"),
    DELETEARTICLES("/fxml/articles/deleteArticles.fxml"),
    SHOWREADERS("/fxml/readers/showReaders.fxml"),
    ADDREADARTICLE("/fxml/readArticle/addReadArticle.fxml");

    private String ruta;
    Screens(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}
