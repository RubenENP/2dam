package ui.screens.login;

import dao.DaoLogin;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ui.common.BaseScreenController;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;

    private DaoLogin daoLogin;

    @Inject
    LoginController(DaoLogin daoLogin){
        this.daoLogin = daoLogin;
    }

    @FXML
    private void login() {
        if(userTextField.getText().equals(daoLogin.getUser()) && passTextField.getText().equals(daoLogin.getPasswd())){
            getPrincipalController().onLoginDone(daoLogin.getUser());
        }
    }
}
