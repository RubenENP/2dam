package ui.screen.articles;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import model.Newspaper;
import services.ServicesArticle;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;

public class DeleteArticlesController extends BaseScreenController {
    ServicesNewspaper servicesNewspaper;
    ServicesArticle servicesArticle;

    @Inject
    public DeleteArticlesController(ServicesNewspaper servicesNewspaper, ServicesArticle servicesArticle) {
        this.servicesNewspaper = servicesNewspaper;
        this.servicesArticle = servicesArticle;
    }

    public ComboBox<Newspaper> newspaperCombo;

    @Override
    public void principalCargado() {
        newspaperCombo.getItems().addAll(servicesNewspaper.getAll().get());
    }

    public void deleteArticles() {
        Either<String, String> deleted = servicesArticle.delete(newspaperCombo.getSelectionModel().getSelectedItem());

        if (deleted.isLeft()){
            getPrincipalController().showAlert(Alert.AlertType.ERROR, deleted.getLeft());
        } else {
            getPrincipalController().showAlert(Alert.AlertType.INFORMATION, deleted.get());
        }
    }
}
