package dao.retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import modelo.locations.Location;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationsAPI {
    @GET("location")
    Single<Location> getAllLocations(@Query("page") int page);
}
