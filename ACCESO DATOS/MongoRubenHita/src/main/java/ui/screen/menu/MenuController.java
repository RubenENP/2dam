package ui.screen.menu;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.common.BaseScreenController;
import ui.common.Screens;

import java.util.Objects;

public class MenuController extends BaseScreenController {
    @FXML
    private ImageView welcome;
    @FXML
    private ImageView paper;

    public void initialize(){
        welcome.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/welcome.jpg"))));
        paper.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/paper.png"))));
    }
}