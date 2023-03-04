package ui.screen.subscribe;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Subscription;
import services.ServicesSubscriptions;
import ui.common.BaseScreenController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SubscribeController extends BaseScreenController {
    @FXML
    private TextField newspaperIdField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker cancelationDatePicker;
    @FXML
    private TableColumn<Integer, Subscription> newspapersColumn;
    @FXML
    private TableColumn<Date, Subscription> startDateColumn;
    @FXML
    private TableColumn<Date, Subscription> cancellationDateColumn;
    @FXML
    private TableView<Subscription> subscribeTable;


    ServicesSubscriptions servicesSubscriptions;

    @Inject
    public SubscribeController(ServicesSubscriptions servicesSubscriptions) {
        this.servicesSubscriptions = servicesSubscriptions;
    }

    @Override
    public void principalCargado() throws IOException, JAXBException {
        setNewspaperList();
    }

    public void initialize(){
        newspapersColumn.setCellValueFactory(new PropertyValueFactory<>("idNewspaper"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        cancellationDateColumn.setCellValueFactory(new PropertyValueFactory<>("cancellationDate"));
    }

    private void setNewspaperList() {
        Either<List<Subscription>, String> listSubscriptions = servicesSubscriptions.getAll(getPrincipalController().getActualUser());

        if (listSubscriptions.isLeft()){
            subscribeTable.getItems().clear();
            subscribeTable.getItems().addAll(listSubscriptions.getLeft());
        } else {
            showAlert(listSubscriptions.get());
        }
    }

    @FXML
    private void subscribe() {
        int idNewspaper = Integer.parseInt(newspaperIdField.getText());
        int idReader = getPrincipalController().getActualUser();
        java.sql.Date startDate = java.sql.Date.valueOf(startDatePicker.getValue());
        java.sql.Date cancellationDate = java.sql.Date.valueOf(cancelationDatePicker.getValue());

        Either<Subscription, String> subscriptions = servicesSubscriptions.addSubscription(idNewspaper, idReader, startDate, cancellationDate);

        if (subscriptions.isLeft()){
            setNewspaperList();
        } else {
            showAlert(subscriptions.get());
        }
    }

    @FXML
    private void unsubscribe() {
        Subscription s = subscribeTable.getSelectionModel().getSelectedItem();
        Either<Subscription,String> deleteSubscription = servicesSubscriptions.delete(s);

        if (deleteSubscription.isLeft()){
            setNewspaperList();
        } else {
            showAlert(deleteSubscription.get());
        }
    }

    private void showAlert(String m){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(m);
        a.show();
    }
}
