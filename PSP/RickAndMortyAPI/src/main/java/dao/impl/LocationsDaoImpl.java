package dao.impl;

import dao.LocationsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.locations.Location;
import modelo.locations.Results;
import dao.retrofit.llamadas.LocationsAPI;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LocationsDaoImpl implements LocationsDao {
    final LocationsAPI locationsAPI;

    @Inject
    LocationsDaoImpl(LocationsAPI locationsAPI){
        this.locationsAPI = locationsAPI;
    }

    public Either<String, List<Results>> getAllLocations() {
        Either<String, List<Results>> respuesta;

        Response<Location> r;

        try {
            r = locationsAPI.getAllLocations(1).execute();

            if (r.isSuccessful()){
                if (r.body() == null){
                    respuesta = Either.left("ERROR No han cargado los sitios");
                }else {
                    int pages = r.body().info().pages();
                    List<Results> resultsList = new ArrayList<>();
                    for (int i = 1; i < pages; i++){
                        resultsList.addAll(Objects.requireNonNull(locationsAPI.getAllLocations(i).execute().body()).results());
                    }
                    respuesta = Either.right(resultsList);
                }
            }else {
                respuesta = Either.left("ERROR No han cargado los sitios");
            }
        }catch (IOException e){
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }
}
