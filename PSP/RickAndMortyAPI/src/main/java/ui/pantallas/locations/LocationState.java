package ui.pantallas.locations;

import javafx.collections.ObservableList;
import modelo.locations.Results;

public record LocationState(ObservableList<Results> getLocations) {

}
