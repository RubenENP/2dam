package ui.screen.login;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Login;
import services.ServicesLogin;
import services.ServicesReaders;
import ui.common.BaseScreenController;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;

    private final ServicesLogin servicesLogin;

    private final ServicesReaders servicesReaders;

    @Inject
    public LoginController(ServicesLogin servicesLogin, ServicesReaders servicesReaders) {
        this.servicesLogin = servicesLogin;
        this.servicesReaders = servicesReaders;
    }

    public void login() {
        String userName = userTextField.getText();
        String passwd = passTextField.getText();

        if (userName.equals("root")){
            if (passwd.equals("root")){
                getPrincipalController().onLoginAdmin();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Bad password");
                a.show();
            }
        } else {
            Either<String, Login> loginUser = servicesLogin.get(userName);

            if(loginUser.isLeft()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(loginUser.getLeft());
                a.show();
            } else {
                getPrincipalController().onLoginDone(loginUser.get().getIdReader());
            }
        }
    }
}
