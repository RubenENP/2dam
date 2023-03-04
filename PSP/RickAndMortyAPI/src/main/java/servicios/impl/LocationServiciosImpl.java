package servicios.impl;

import dao.LocationsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.locations.Results;
import servicios.LocationServicios;

import java.io.IOException;
import java.util.List;

public class LocationServiciosImpl implements LocationServicios {
    final LocationsDao locationsDao;

    @Inject
    LocationServiciosImpl(LocationsDao locationsDao){
        this.locationsDao = locationsDao;
    }

    public Either<String, List<Results>> getAllLocations(){
        Either<String, List<Results>> respuesta;

        try {
            Either<String, List<Results>> loc = locationsDao.getAllLocations();
            if (loc.isLeft()){
                respuesta = Either.left(loc.getLeft());
            }else {
                respuesta = Either.right(loc.get());
            }
        } catch (IOException e){
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }
}
