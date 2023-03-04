package ui.screens.menu;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicesUser;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class MenuController extends BasePantallaController {
    private MenuViewModel menuViewModel;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    @Inject
    public MenuController(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
    }

    public void initialize() {
        menuViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            Alert a;
            if (stateNew.error() != null){
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(stateNew.error());
                a.show();
            } else {
                if (stateNew.user().getUsername().equals("admin")) {
                    getPrincipalController().menuBar.setDisable(false);
                    getPrincipalController().cargarPantalla(Pantallas.ADMINMENU);
                } else {
                    getPrincipalController().menuBar.setDisable(false);
                    getPrincipalController().cargarPantalla(Pantallas.USERMENU);
                }
            }
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @FXML
    private void login() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        menuViewModel.login(userField.getText(), passField.getText());
    }
}
