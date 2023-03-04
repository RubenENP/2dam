package ui.screen.readArticle;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.readArticle.ReadArticle;
import services.ServicesReadArticle;
import ui.common.BaseScreenController;

import java.util.List;

public class RateReadArticleController extends BaseScreenController {

    public TableView<ReadArticle> readArticlesTable;
    public TextField ratingField;
    public TableColumn<ReadArticle, String> idColumn;
    public TableColumn<ReadArticle, String> idArticleColumn;
    public TableColumn<ReadArticle, String> rankingColumn;

    ServicesReadArticle servicesReadArticle;

    @Inject
    public RateReadArticleController(ServicesReadArticle servicesReadArticle) {
        this.servicesReadArticle = servicesReadArticle;
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idArticleColumn.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        rankingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));
    }

    @Override
    public void principalCargado(){
        setTable();
    }

    private void setTable(){
        readArticlesTable.getItems().clear();

        List<ReadArticle> articleList = servicesReadArticle.getAll().getLeft()
                        .stream().filter(readArticle -> readArticle.getIdReader()== getPrincipalController().getActualUser())
                        .toList();

        readArticlesTable.getItems().addAll(articleList);
    }
    public void rate() {
        ReadArticle update = servicesReadArticle.update(readArticlesTable.getSelectionModel().getSelectedItem(),
                Integer.parseInt(ratingField.getText()));

        if (update != null){
            setTable();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("ERROR");
            a.show();
        }
    }
}
