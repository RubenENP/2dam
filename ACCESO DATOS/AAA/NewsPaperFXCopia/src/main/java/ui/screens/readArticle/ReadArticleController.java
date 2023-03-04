package ui.screens.readArticle;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import model.readArticle.ReadArticle;
import services.ServicesArticles;
import services.ServicesReadArticle;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.io.IOException;

@Log4j2
public class ReadArticleController extends BaseScreenController {
    @FXML
    private ComboBox<String> comboArticle;
    @FXML
    private ComboBox<String> comboReader;
    @FXML
    private TextField textRating;
    @FXML
    private TextField textId;
    @FXML
    private TableView<ReadArticle> readArticlesTable;
    @FXML
    private TableColumn<String, ReadArticle> idColumn;
    @FXML
    private TableColumn<String, ReadArticle> idReaderColumn;
    @FXML
    private TableColumn<String, ReadArticle> idArticleColumn;
    @FXML
    private TableColumn<String, ReadArticle> ratingColumn;

    ServicesReadArticle servicesReadArticle;
    ServicesReaders servicesReaders;
    ServicesArticles servicesArticles;

    @Inject
    ReadArticleController (ServicesReadArticle servicesReadArticle, ServicesReaders servicesReaders, ServicesArticles servicesArticles){
        this.servicesReadArticle = servicesReadArticle;
        this.servicesReaders = servicesReaders;
        this.servicesArticles = servicesArticles;
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idReaderColumn.setCellValueFactory(new PropertyValueFactory<>("idReader"));
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        setItems();
    }

    private void setItems() {
        if (servicesReadArticle.getAll().isLeft()){
            readArticlesTable.getItems().addAll(servicesReadArticle.getAll().getLeft());
        } else {
            showAlert(servicesReadArticle.getAll().get());
        }

        if(servicesReaders.getReadersName().isLeft()){
            if (servicesArticles.getAllArticlesName().isLeft()) {
                comboReader.getItems().clear();
                comboReader.getItems().addAll(servicesReaders.getReadersName().getLeft());

                comboArticle.getItems().clear();
                comboArticle.getItems().addAll(servicesArticles.getAllArticlesName().getLeft());
            }else {
                showAlert(servicesArticles.getAllArticlesName().get());
            }
        }else {
            showAlert(servicesReaders.getReadersName().get());
        }
    }

    private void showAlert(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR "+m);
        a.show();
    }

    @FXML
    private void addReadArticle() {
        try {
            servicesReadArticle.addReadArticle(textId.getText()
                    , comboReader.getSelectionModel().getSelectedItem()
                    , comboArticle.getSelectionModel().getSelectedItem()
                    , textRating.getText());

            setItems();
        } catch (IOException | JAXBException e){
            log.error(e.getMessage());
        }
    }
}
