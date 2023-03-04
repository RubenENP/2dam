package ui.screen.articles;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.Newspaper;
import services.ServicesArticle;
import services.ServicesArticleType;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

public class ShowArticlesController extends BaseScreenController {
    public ComboBox<Newspaper> newspaperCombo;
    public TableColumn<String, Article> typeArticleColumn;
    @FXML
    private Label articleTypeNameLabel;
    @FXML
    private TextField rankingField;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<String, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleArticleColumn;
    @FXML
    private TableColumn<String, Article> descriptionArticleColumn;

    private final ServicesArticle servicesArticle;
    private final ServicesArticleType servicesArticleType;
    private final ServicesNewspaper servicesNewspaper;

    @Inject
    public ShowArticlesController(ServicesArticle servicesArticle, ServicesArticleType servicesArticleType,
                                  ServicesNewspaper servicesNewspaper) {
        this.servicesArticle = servicesArticle;
        this.servicesArticleType = servicesArticleType;
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize() {
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        typeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

//    @Override
//    public void principalCargado() throws IOException, JAXBException {
//        Either<String, List<Article>> articles = servicesArticle.getAll();

//        if (articles.isLeft()) {
//            showAlert(articles.getLeft());
//        } else {
//            articlesTable.getItems().addAll(articles.get());
//        }

//        Either<String, ArticleTypeHib> articleType = servicesArticleType.getReadestArticleType();
//
//        if (articleType.isLeft()){
//            showAlert(articleType.getLeft());
//        } else {
//            articleTypeNameLabel.setText(articleType.get().getDescription());
//        }
//    }

    @Override
    public void principalCargado() {
        newspaperCombo.getItems().addAll(servicesNewspaper.getAll().get());
    }

    public void show() {
        Either<String, List<Article>> getAll = servicesArticle.getAll(newspaperCombo.getSelectionModel().getSelectedItem());
        if (getAll.isLeft()){
            getPrincipalController().showAlert(Alert.AlertType.ERROR, getAll.getLeft());
        } else {
            articlesTable.getItems().clear();
            articlesTable.getItems().addAll(getAll.get());
        }
    }

}
