package ui.pantallas.locations;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import modelo.locations.Results;
import servicios.LocationServicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class LocationsViewModel {
    final LocationServicios locationServicios;

    @Inject
    LocationsViewModel(LocationServicios locationServicios) {
        this.locationServicios = locationServicios;

        state = new SimpleObjectProperty<>(new LocationState(FXCollections.observableArrayList(), null));
    }

    private final ObjectProperty<LocationState> state;

    public ReadOnlyObjectProperty<LocationState> getState() {
        return state;
    }

    public void getLocationNames() {
        locationServicios.getAllLocations()
                .delay(5, TimeUnit.SECONDS)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                   LocationState locationState;

                   if (either.isLeft()){
                       locationState = new LocationState(null, either.getLeft());
                   } else {
                       locationState = new LocationState(either.get(), null);
                   }

                   state.setValue(locationState);
                });
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
