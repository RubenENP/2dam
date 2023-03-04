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
            Either<String, ReaderHib> readers = servicesReaders.get(getPrincipalController().getActualUser());

            if (readers.isLeft()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(readers.getLeft());
                a.show();
            } else {
                user.setText(readers.get().getNameReader());
            }

//            Either<List<Reader>, String> allReaders = servicesReaders.getAll();
//            if (allReaders.isLeft()){
//                String name = allReaders.getLeft().stream().filter(reader -> reader.getId() == getPrincipalController().getActualUser())
//                        .findFirst().orElse(null).getName();
//
//                user.setText(name);
//
//            } else {
//                Alert a = new Alert(Alert.AlertType.ERROR);
//                a.setContentText(allReaders.get());
//                a.show();
//            }
        }
    }
}