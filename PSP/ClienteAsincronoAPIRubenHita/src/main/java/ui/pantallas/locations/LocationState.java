package ui.pantallas.locations;

import modelo.locations.Results;

import java.util.List;

public record LocationState(List<Results> getLocations, String error) {

}
