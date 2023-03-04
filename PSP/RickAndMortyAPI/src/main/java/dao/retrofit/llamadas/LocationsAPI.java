package dao.retrofit.llamadas;

import modelo.locations.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationsAPI {
    @GET("location")
    Call<Location> getAllLocations(@Query("page") int page);
}
