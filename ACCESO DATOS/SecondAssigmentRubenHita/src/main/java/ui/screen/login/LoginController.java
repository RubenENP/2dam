package ui.screen.login;

import dao.DaoLogin;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicesLogin;
import ui.common.BaseScreenController;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;

    private final ServicesLogin servicesLogin;

    @Inject
    LoginController(ServicesLogin servicesLogin){
        this.servicesLogin = servicesLogin;
    }

    @FXML
    private void login() {
        String userName = userTextField.getText();
        String passwd = passTextField.getText();

        if (userName.equals("root")){
            boolean loginUser = servicesLogin.loginAdmin(passwd);

            if(loginUser){
                getPrincipalController().onLoginAdmin();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Bad password");
                a.show();
            }
        } else {
            Either<Integer, String> loginUser = servicesLogin.loginUser(userName, passwd);

            if(loginUser.isLeft()){
                getPrincipalController().onLoginDone(loginUser.getLeft());
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(loginUser.get());
                a.show();
            }
        }
    }
}
