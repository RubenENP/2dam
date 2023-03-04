package ui.screen.articles;

import jakarta.xml.bind.JAXBException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Query;
import ui.common.BaseScreenController;

import java.io.IOException;

public class DescriptionsController extends BaseScreenController {

    public TableView<Query> queryTable;
    public TableColumn<Query, String> descriptionColumn;
    public TableColumn<Query, String> numReaderColumn;
    public TableColumn<Query, String> nameArticleColumn;

//    ServicesQuery servicesQuery;
//
//    public DescriptionsController(ServicesQuery servicesQuery) {
//        this.servicesQuery = servicesQuery;
//    }
//
//    public void initialize(){
//        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        numReaderColumn.setCellValueFactory(new PropertyValueFactory<>("numReader"));
//        nameArticleColumn.setCellValueFactory(new PropertyValueFactory<>("name_article"));
//    }
//
//    @Override
//    public void principalCargado() throws IOException, JAXBException {
//        queryTable.getItems().clear();
//        queryTable.getItems().addAll(servicesQuery.getQuery().getLeft());
//    }
}
