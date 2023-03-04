package ui.pantallas.locations;

import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import modelo.locations.Results;
import ui.common.BasePantallaController;
import ui.common.Pantallas;

import java.time.LocalDate;


public class LocationsController extends BasePantallaController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private Label dimensionLbl;
    @FXML
    private Label tipoLbl;
    @FXML
    private ListView<String> nombreLugaresLista;
    final LocationsViewModel locationsViewModel;

    @Inject
    LocationsController (LocationsViewModel locationsViewModel){
        this.locationsViewModel = locationsViewModel;
    }

    public void initialize() {
        cambiosEstado();
    }

    @Override
    public void principalCargado() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        locationsViewModel.getLocationNames();
    }

    public void atras() {
        getPrincipalController().cargarPantalla(Pantallas.MENU);
    }

    public void verLugar() {
        String name = nombreLugaresLista.getSelectionModel().getSelectedItem();

        String type = locationsViewModel.getLocationType(name);
        tipoLbl.setText("Tipo: "+type);

        String dimension = locationsViewModel.getDimension(name);
        dimensionLbl.setText("Dimension: "+dimension);

        LocalDate l = locationsViewModel.getLocationDate(name);

        datePicker.setValue(l);
    }

    private void cambiosEstado() {
        locationsViewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> Platform.runLater(() -> {
            if (listadoStateNew.error() != null) {
                getPrincipalController().sacarAlertError(listadoStateNew.error());
            }
            if (listadoStateNew.getLocations() != null) {
                nombreLugaresLista.getItems().clear();
                nombreLugaresLista.getItems().addAll(listadoStateNew.getLocations().stream().map(Results::name).toList());
            }

            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        }));
    }
}
