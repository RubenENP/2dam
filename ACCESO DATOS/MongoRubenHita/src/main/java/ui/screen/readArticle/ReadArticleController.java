package ui.screen.readArticle;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.Newspaper;
import model.ReadArticle;
import services.ServicesNewspaper;
import services.ServicesReadArticle;
import services.ServicesReader;
import ui.common.BaseScreenController;

import java.io.IOException;

public class ReadArticleController extends BaseScreenController {

    public TableView<Newspaper> newspaperTable;
    public TableColumn<String, Newspaper> idNewspaperColumn;
    public TableColumn<String, Newspaper> nameNewspaperColumn;
    public TableColumn<String, Newspaper> releaseDateNewspaperColumn;
    public TableView<Article> articlesTable;
    public TableColumn<String, Article> idArticleColumn;
    public TableColumn<String, Article> titleArticleColumn;
    public TableColumn<String, Article> descriptionArticleColumn;
    public TableColumn<String, Article> typeArticleColumn;
    public TextField rateField;

    ServicesNewspaper servicesNewspaper;
    ServicesReadArticle servicesReadArticle;

    @Inject
    public ReadArticleController(ServicesNewspaper servicesNewspaper, ServicesReadArticle servicesReadArticle) {
        this.servicesNewspaper = servicesNewspaper;
        this.servicesReadArticle = servicesReadArticle;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("_id"));
        nameNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("relDate"));

        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleArticleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionArticleColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        typeArticleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        newspaperTable.getItems().addAll(servicesNewspaper.getAll().get());

        newspaperTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldNewspaper, newNewspaper) -> {
            articlesTable.getItems().clear();
            articlesTable.getItems().addAll(newNewspaper.getArticles());
        });
    }

    public void rate() {
        ReadArticle readArticle = new ReadArticle(articlesTable.getSelectionModel().getSelectedItem().getId(),
                Integer.parseInt(rateField.getText()));
        Either<String, ReadArticle> addReadArticle = servicesReadArticle.add(readArticle, newspaperTable.getSelectionModel().getSelectedItem(), articlesTable.getSelectionModel().getSelectedItem());

        if (addReadArticle.isLeft()) {
            getPrincipalController().showAlert(Alert.AlertType.ERROR, addReadArticle.getLeft());
        } else {
            getPrincipalController().showAlert(Alert.AlertType.INFORMATION, "ReadArticle added.");
        }
    }
}
