package ui.screens.artciles;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.ArticleType;
import services.ServicesArticleType;
import services.ServicesArticles;
import ui.common.BaseScreenController;

public class ShowArticlesController extends BaseScreenController {
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<String, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleArticleColumn;
    @FXML
    private TableColumn<String, Article> descriptionArticleColumn;
    @FXML
    private TableColumn<String, Article> idNewspaperArticleColumn;
    @FXML
    private TableColumn<String, Article> idTypeArticleColumn;
    @FXML
    private TableColumn<String, Article> authorArticleColumn;

    ServicesArticles servicesArticles;

    ServicesArticleType servicesArticleType;

    @Inject
    ShowArticlesController(ServicesArticles servicesArticles, ServicesArticleType servicesArticleType){
        this.servicesArticles = servicesArticles;
        this.servicesArticleType = servicesArticleType;
    }

    public void initialize(){
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        idNewspaperArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idNewspaper"));
        idTypeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idType"));
        authorArticleColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    }

    @Override
    public void principalCargado() {
        setComboBox();
    }

    private void setComboBox() {
        typeComboBox.getItems().clear();
        typeComboBox.getItems().addAll(servicesArticleType.getAllNameTypes());
    }

    public void showArticles() {
        articlesTable.getItems().clear();
        int idType = servicesArticleType.getAllTypes().stream()
                .filter(article -> article.getName().equals(typeComboBox.getSelectionModel().getSelectedItem()))
                .map(ArticleType::getId).findFirst().orElse(null);

        articlesTable.getItems().addAll(servicesArticles.getArticlesByType(idType));
    }
}
