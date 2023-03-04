package ui.screens.login;

import dao.DaoLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.common.BaseScreenController;
import ui.common.Screens;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField userTextField;
    @FXML
    private TextField passTextField;

    private DaoLogin daoLogin;

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
