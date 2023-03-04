package ui.screens.adminmenu;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.User;
import ui.common.BasePantallaController;

public class AdminController extends BasePantallaController {
    @FXML
    private ListView<String> usersListView;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    private final AdminViewModel adminViewModel;

    @Inject
    public AdminController(AdminViewModel adminViewModel) {
        this.adminViewModel = adminViewModel;
    }

    @Override
    public void principalCargado() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        adminViewModel.getAllUsers();
    }

    public void initialize(){
        adminViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            Alert a;
            if (stateNew.error() != null){
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(stateNew.error());
                a.show();
            }

            if (stateNew.user() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(stateNew.user().getUsername()+" añadido.");
                a.show();
            }

            if (stateNew.userList() != null){
                usersListView.getItems().clear();
                usersListView.getItems().addAll(stateNew.userList().stream().map(User::getUsername).toList());
            }
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }



    @FXML
    private void register() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        User u = new User(userField.getText(), passField.getText(), "user");
        adminViewModel.postUser(u);
        adminViewModel.getAllUsers();
    }
}
