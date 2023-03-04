package ui.screen.articles;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.ArticleTypeHib;
import model.ReadArticle;
import services.ServicesArticle;
import services.ServicesArticleType;
import services.ServicesReadArticle;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

public class ShowArticlesController extends BaseScreenController {
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
    private final ServicesReadArticle servicesReadArticle;
    private final ServicesArticleType servicesArticleType;

    @Inject
    public ShowArticlesController(ServicesArticle servicesArticle, ServicesReadArticle servicesReadArticle, ServicesArticleType servicesArticleType) {
        this.servicesArticle = servicesArticle;
        this.servicesReadArticle = servicesReadArticle;
        this.servicesArticleType = servicesArticleType;
    }

    public void initialize() {
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("nameArticle"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        Either<String, List<Article>> articles = servicesArticle.getAll();

        if (articles.isLeft()) {
            showAlert(articles.getLeft());
        } else {
            articlesTable.getItems().addAll(articles.get());
        }

        Either<String, ArticleTypeHib> articleType = servicesArticleType.getReadestArticleType();

        if (articleType.isLeft()){
            showAlert(articleType.getLeft());
        } else {
            articleTypeNameLabel.setText(articleType.get().getDescription());
        }
    }

    @FXML
    private void read() {
        try {
            ReadArticle readArticle = new ReadArticle(0, articlesTable.getSelectionModel().getSelectedItem(),
                    articlesTable.getSelectionModel().getSelectedItem().getId(),
                    getPrincipalController().getActualUser(), Integer.parseInt(rankingField.getText()));
            servicesReadArticle.add(readArticle);
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR " + m);
        a.show();
    }
}
