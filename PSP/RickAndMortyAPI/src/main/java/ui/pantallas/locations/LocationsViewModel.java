package ui.pantallas.locations;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import modelo.locations.Results;
import servicios.LocationServicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LocationsViewModel {
    final LocationServicios locationServicios;

    @Inject
    LocationsViewModel(LocationServicios locationServicios) {
        this.locationServicios = locationServicios;

        state = new SimpleObjectProperty<>(new LocationState(FXCollections.observableArrayList()));
        setState();
    }

    private void setState() {
        Either<String, List<Results>> locations = locationServicios.getAllLocations();

        if (locations.isLeft()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(locations.getLeft());
            a.show();
        }else {
            state.get().getLocations().addAll(locations.get());
        }
    }

    private final ObjectProperty<LocationState> state;

    public ReadOnlyObjectProperty<LocationState> getState() {
        return state;
    }

    public List<String> getLocationNames() {
        return getState().get().getLocations().stream().map(Results::name).toList();
    }

    public String getLocationType(String name) {
        return getState().get().getLocations().stream().filter(nombre -> nombre.name().equals(name))
                .toList().stream().map(Results::type).toList().get(0);
    }

    public String getDimension(String name) {
        return getState().get().getLocations().stream().filter(nombre -> nombre.name().equals(name))
                .toList().stream().map(Results::dimension).toList().get(0);
    }

    public LocalDate getLocationDate(String name) {
        return LocalDate.parse(getState().get().getLocations().stream().filter(nombre -> nombre.name().equals(name))
                        .toList().stream().map(Results::created).toList().get(0)
                        .substring(0,10)
                , DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
