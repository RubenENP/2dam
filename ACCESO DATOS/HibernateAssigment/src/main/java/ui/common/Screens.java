package ui.common;

public enum Screens {
    LOGIN("/fxml/login.fxml"),
    MENU("/fxml/menu.fxml"),
    ADDNEWSPAPER("/fxml/newspaper/addNewspaper.fxml"),
    SHOWALLNEWSPAPERS("/fxml/newspaper/showNewspapers.fxml"),
    SHOWARTICLES("/fxml/articles/showArticles.fxml"),
    ADDARTICLES("/fxml/articles/addArticles.fxml"),
    DELETENEWSPAPER("/fxml/newspaper/deleteNewspaper.fxml"),
    DELETEREADER("/fxml/readers/deleteReaders.fxml"),
    SHOWREADERS("/fxml/readers/showReaders.fxml"),
    ADDREADARTICLE("/fxml/readArticles/addReadArticle.fxml"),
    ADDREADER("/fxml/readers/addReader.fxml"),
    UPDATEREADER("/fxml/readers/updateReaders.fxml"),
    SUBSCRIBE("/fxml/subscribe/subscribe.fxml"),
    RATEARTICLE("/fxml/readArticles/rateArticle.fxml"),
    QUERYARTICLES("/fxml/articles/descriptions.fxml"),
    DELETEARTICLES("/fxml/articles/deleteArticles.fxml");
    private String ruta;
    Screens(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}
