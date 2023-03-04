package ui.screen.articles;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.NewspaperHib;
import services.ServicesArticle;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.List;

public class DeleteArticlesController extends BaseScreenController {
    private final ServicesArticle servicesArticle;
    private final ServicesNewspaper servicesNewspaper;
    @FXML
    private TableView<Article> articlesTable;
    @FXML
    private TableColumn<String, Article> idArticleColumn;
    @FXML
    private TableColumn<String, Article> titleArticleColumn;
    @FXML
    private TableColumn<String, Article> descriptionArticleColumn;
    @FXML
    private ComboBox<String> comboNewspapers;

    private List<NewspaperHib> newspaperList;

    @Inject
    public DeleteArticlesController(ServicesArticle servicesArticle, ServicesNewspaper servicesNewspaper) {
        this.servicesArticle = servicesArticle;
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize() {
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("nameArticle"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        updateTable();
    }

    private void updateTable() {
        Either<String, List<Article>> articles = servicesArticle.getAll();

        if (articles.isLeft()) {
            showAlert(articles.getLeft());
        } else {
            articlesTable.getItems().addAll(articles.get());
        }

        newspaperList = servicesNewspaper.getAll().get();

        comboNewspapers.getItems().addAll(newspaperList
                .stream().map(NewspaperHib::getNameNewspaper).toList());
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(m);
        a.show();
    }

    @FXML
    private void delete() {
        String newspaperName = comboNewspapers.getSelectionModel().getSelectedItem();
        int id = newspaperList.stream().filter(newspaperHib -> newspaperHib.getNameNewspaper().equals(newspaperName)).findFirst().orElse(new NewspaperHib()).getId();
        Either<String, List<Article>> deletedArticles = servicesArticle.delete(id);

        if (deletedArticles.isLeft()) {
            showAlert(deletedArticles.getLeft());
        } else {
            updateTable();
        }
    }
}
