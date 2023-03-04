package ui.screen.menu;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ReaderHib;
import services.ServicesReaders;
import ui.common.BaseScreenController;

import java.util.Objects;

public class MenuController extends BaseScreenController {
    @FXML
    private Label user;
    @FXML
    private ImageView welcome;
    @FXML
    private ImageView paper;

    ServicesReaders servicesReaders;
    @Inject
    MenuController(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    public void initialize(){
        welcome.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/welcome.jpg"))));
        paper.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/paper.png"))));
    }

    @Override
    public void principalCargado() {
        if (getPrincipalController().isAdminLogged()){
            user.setText("root");
        } else {
            user.setText(getPrincipalController().getActualUser().getNameReader());
        }
    }
}