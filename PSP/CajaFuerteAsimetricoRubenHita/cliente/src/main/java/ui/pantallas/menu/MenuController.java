package ui.pantallas.menu;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
            }

            if (stateNew.userReg() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Usuario registrado correctamente");
                a.show();
            }
            if (stateNew.user() != null){
                getPrincipalController().menuBar.setDisable(false);
                getPrincipalController().cargarPantalla(Pantallas.USERMENU);
            }
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @Override
    public void principalCargado() throws IOException {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        menuViewModel.getServerCertificate();
    }

    @FXML
    private void login() throws NoSuchAlgorithmException {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        menuViewModel.login(userField.getText(), passField.getText());
    }

    @FXML
    private void register() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        menuViewModel.register(userField.getText(), passField.getText());
    }
}
