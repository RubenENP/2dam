package ui.pantallas.login;

import com.fasterxml.jackson.databind.ser.Serializers;
import domain.model.Usuario;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

public class LoginController extends BasePantallaController {
    @FXML
    private Label pista;
    @FXML
    private TextField usuarioRecuperarField;
    @FXML
    private TextField userFieldLog;
    @FXML
    private PasswordField contrasenyaFieldLog;
    @FXML
    private TextField userFieldReg;
    @FXML
    private TextField emailFieldReg;
    @FXML
    private PasswordField contrasenyaFieldReg;

    private final LoginViewModel loginViewModel;

    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize(){
        loginViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            Alert a;
            if (stateNew.usuarioRegister() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Usuario "+stateNew.usuarioRegister().getUser()+" registrado");
            } else if (stateNew.usuarioLogin() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Usuario "+stateNew.usuarioLogin().getUser()+" logueado");
            } else if (stateNew.cambiarPass() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(stateNew.cambiarPass());
            } else if (stateNew.logout() != null){
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(stateNew.logout());
            }
            else {
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(stateNew.error());
            }
            a.show();

            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }

    @FXML
    private void login() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        loginViewModel.login(userFieldLog.getText(), contrasenyaFieldLog.getText());
    }

    @FXML
    private void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    @FXML
    private void register() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        loginViewModel.register(userFieldReg.getText(), contrasenyaFieldReg.getText(), emailFieldReg.getText());
    }

    @FXML
    private void recuperarPassword() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        loginViewModel.usuarioRecuperarField(usuarioRecuperarField.getText());
    }
    @FXML
    private void logout() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        loginViewModel.logout();
    }
}
