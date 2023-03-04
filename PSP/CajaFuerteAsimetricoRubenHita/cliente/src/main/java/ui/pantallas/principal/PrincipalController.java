package ui.pantallas.principal;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.io.IOException;

@Log4j2
public class PrincipalController extends BasePantallaController {
    @FXML
    public BorderPane root;
    public MenuBar menuBar;

    private Stage primaryStage;

    final Instance<Object> instance;

    private Alert alert;

    private final PrincipalViewModel principalViewModel;

    @Inject
    public PrincipalController(Instance<Object> instance, PrincipalViewModel principalViewModel) {
        this.instance = instance;
        this.principalViewModel = principalViewModel;
    }

    public void initialize() {
        cargarPantalla(Pantallas.MENU);

        principalViewModel.getState().addListener((observableValue, listadoState, stateNew) -> Platform.runLater(() -> {
            Alert a;
            if (stateNew.error() != null){
                a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(stateNew.error());
            } else {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText(stateNew.logout());
                cargarPantalla(Pantallas.MENU);
            }

            a.show();
            root.setCursor(Cursor.DEFAULT);
        }));

        menuBar.setDisable(true);
    }

    public void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    public void sacarAlertError(String mensaje)
    {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    @FXML
    private void logout() {
        root.setCursor(Cursor.WAIT);
        principalViewModel.logout("logout");
        cargarPantalla(Pantallas.MENU);
    }
}
