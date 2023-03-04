package servicios.impl;

import dao.LocationsDao;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.locations.Results;
import servicios.LocationServicios;

import java.util.List;

public class LocationServiciosImpl implements LocationServicios {
    final LocationsDao locationsDao;

    @Inject
    LocationServiciosImpl(LocationsDao locationsDao){
        this.locationsDao = locationsDao;
    }

    public Single<Either<String, List<Results>>> getAllLocations(){
        return locationsDao.getAllLocations();
    }
}
