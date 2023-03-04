package ui.screens.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ui.common.BaseScreenController;

import java.util.Objects;

public class MenuController extends BaseScreenController {
    @FXML
    private Label user;
    @FXML
    private ImageView welcome;
    @FXML
    private ImageView paper;

    public void initialize(){
        welcome.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/welcome.jpg"))));
        paper.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/paper.png"))));
    }

    @Override
    public void principalCargado() {
        user.setText(getPrincipalController().actualUser);
    }
}