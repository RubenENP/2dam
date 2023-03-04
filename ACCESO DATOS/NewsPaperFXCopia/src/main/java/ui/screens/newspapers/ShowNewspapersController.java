package ui.screens.newspapers;

import dao.DaoArticles;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.Newspaper;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

public class ShowNewspapersController extends BaseScreenController {
    @FXML
    private TableColumn<Integer, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleArticleColumn;
    @FXML
    private TableColumn<String, Article> descriptionArticleColumn;
    @FXML
    private TableColumn<Integer, Article> idNewspaperArticleColumn;
    @FXML
    private TableColumn<Integer, Article> idTypeArticleColumn;
    @FXML
    private TableColumn<String, Article> authorArticleColumn;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<Integer, Newspaper> idNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> nombreNewspaperColumn;
    @FXML
    private TableColumn<Double, Newspaper> precioNewspaperColumn;
    @FXML
    private TableColumn<String, Newspaper> directorNewspaperColumn;

    ServicesNewspaper servicesNewspaper;
    DaoArticles daoArticles;

    @Inject
    ShowNewspapersController(ServicesNewspaper servicesNewspaper, DaoArticles daoArticles){
        this.servicesNewspaper = servicesNewspaper;
        this.daoArticles = daoArticles;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
        directorNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("director"));

        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        idNewspaperArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idNewspaper"));
        idTypeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idType"));
        authorArticleColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void setTables() throws IOException {
        newspaperTable.getItems().clear();
        newspaperTable.getItems().addAll(servicesNewspaper.getAll());
    }

    @FXML
    private void getArticle() {
        int id = newspaperTable.getSelectionModel().getSelectedItem().getId();

        List<Article> articleList = daoArticles.getAll().stream().filter(articles -> articles.getIdNewspaper() == id).toList();

        articlesTable.getItems().clear();
        articlesTable.getItems().addAll(articleList);
    }
}
