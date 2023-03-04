package ui.screen.newspapers;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Article;
import model.NewspaperHib;
import services.ServicesNewspaper;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ShowNewspapersController extends BaseScreenController {
    public TableView<Article> articleTable;
    public TableColumn<String, Article> articleNameColumn;
    public TableColumn<String, Article> articleDescriptionColumn;
    public TableColumn<String, Article> idTypeColumn;
    public ListView<String> nametypeList;
    public ListView<Integer> numberTypeList;
    @FXML
    private TableView<NewspaperHib> newspaperTable;
    @FXML
    private TableColumn<Integer, NewspaperHib> idNewspaperColumn;
    @FXML
    private TableColumn<String, NewspaperHib> nameNewspaperColumn;
    @FXML
    private TableColumn<Date, NewspaperHib> releaseDateNewspaperColumn;

    ServicesNewspaper servicesNewspaper;

    @Inject
    ShowNewspapersController(ServicesNewspaper servicesNewspaper){
        this.servicesNewspaper = servicesNewspaper;
    }

    public void initialize(){
        idNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("nameNewspaper"));
        releaseDateNewspaperColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        articleNameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        articleDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("nameArticle"));
        idTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @Override
    public void principalCargado() throws IOException {
        setTables();
    }

    private void setTables() {
        Either<String, List<NewspaperHib>>  newspapers = servicesNewspaper.getAll();

        if(newspapers.isLeft()){
            showAlert(newspapers.getLeft());
        } else {
            newspaperTable.getItems().clear();
            newspaperTable.getItems().addAll(newspapers.get());
        }

        newspaperTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null){
                Either<String, NewspaperHib> newspaper = servicesNewspaper.get(newSelection);

                if (newspaper.isLeft()){
                    showAlert(newspaper.getLeft());
                } else {
                    articleTable.getItems().clear();
                    articleTable.getItems().addAll(newspaper.get().getArticleList());

                    Map<String, Integer> map = servicesNewspaper.getNbrArticles(newspaper.get().getId());

                    nametypeList.getItems().clear();
                    numberTypeList.getItems().clear();

                    for(Map.Entry<String, Integer> m : map.entrySet()){
                        nametypeList.getItems().add(m.getKey());
                        numberTypeList.getItems().add(m.getValue());
                    }
                }
            }
        });
    }

    private void showAlert(String m){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("ERROR "+m);
        a.show();
    }

}
