package ui.screens.artciles;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.ArticleType;
import model.Newspaper;
import services.ServicesArticleType;
import services.ServicesArticles;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.Objects;

public class AddArticlesController extends BaseScreenController {

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
    @FXML
    private TextField idTxtField;
    @FXML
    private TextField titleTxtField;
    @FXML
    private TextField authorTxtField;
    @FXML
    private ComboBox<String> newspapersComboBox;
    @FXML
    private ComboBox<String> typesComboBox;
    @FXML
    private TextArea descriptionTxtField;

    ServicesArticleType servicesArticleType;
    ServicesNewspaper servicesNewspaper;
    ServicesArticles servicesArticles;

    @Inject
    AddArticlesController(ServicesArticleType servicesArticleType, ServicesNewspaper servicesNewspaper, ServicesArticles servicesArticles) {
        this.servicesArticleType = servicesArticleType;
        this.servicesNewspaper = servicesNewspaper;
        this.servicesArticles = servicesArticles;
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
        setComboBoxes();

        setTable();
    }

    private void setTable() {
        articlesTable.getItems().clear();
        articlesTable.getItems().addAll(servicesArticles.getAllArticles());
    }

    private void setComboBoxes() {
        newspapersComboBox.getItems().addAll(servicesNewspaper.getAll().stream().map(Newspaper::getNombre).toList());
        typesComboBox.getItems().addAll(servicesArticleType.getAllTypes().stream().map(ArticleType::getName).toList());
    }

    public void addArticle() {
        String id = idTxtField.getText();

        String title = titleTxtField.getText();

        String description = descriptionTxtField.getText();

        String idNewspaper = Objects.requireNonNull(servicesNewspaper.getAll().stream()
                .filter(newspaper -> newspaper.getNombre().equals(newspapersComboBox.getSelectionModel().getSelectedItem()))
                .map(Newspaper::getId).findFirst().orElse(null)).toString();

        String idType = Objects.requireNonNull(servicesArticleType.getAllTypes().stream()
                .filter(type -> type.getName().equals(typesComboBox.getSelectionModel().getSelectedItem()))
                .map(ArticleType::getId).findFirst().orElse(null)).toString();

        String author = authorTxtField.getText();

        if (!servicesArticles.addArticle(id+";"+title+";"+description+";"+idNewspaper+";"+idType+";"+author)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("ERROR");
            a.show();
        }else {
            setTable();
        }
    }
}
