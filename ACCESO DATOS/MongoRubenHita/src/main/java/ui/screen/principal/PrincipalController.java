package ui.screen.principal;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import lombok.extern.log4j.Log4j2;
import ui.common.BaseScreenController;
import ui.common.Screens;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {
    @FXML
    private MenuBar menu;
    @FXML
    private BorderPane root;

    private Stage primaryStage;

    Instance<Object> instance;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
    }

    public void initialize() {
        menu.setVisible(true);
        cargarPantalla(Screens.MENU);
    }

    public void cargarPantalla(Screens pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText("Are you sure you want to close?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void exit() {
        primaryStage.close();
        Platform.exit();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    public void showAlert(Alert.AlertType type, String msg){
        Alert a = new Alert(type);
        a.setContentText(msg);
        a.show();
    }

    public void backToWelcome() {
        cargarPantalla(Screens.MENU);
    }

    public void showNewspapers() {
        cargarPantalla(Screens.SHOWALLNEWSPAPERS);
    }

    public void showArticles() {
        cargarPantalla(Screens.SHOWARTICLES);
    }

    public void addReader() {cargarPantalla(Screens.ADDREADERS);}

    public void updateReader() {cargarPantalla(Screens.UPDATEREADERS);}

    public void deleteNewspaper() {cargarPantalla(Screens.DELETENEWSPAPER);}

    public void deleteReader() {cargarPantalla(Screens.DELETEREADER);}

    public void deleteArticles() {cargarPantalla(Screens.DELETEARTICLES);}

    public void showReaders() {cargarPantalla(Screens.SHOWREADERS);}

    public void addReadArticle() {cargarPantalla(Screens.ADDREADARTICLE);}
}
