package retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import modelo.Carpeta;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface CarpetaAPI {
    @GET("carpeta/")
    Single<List<Carpeta>> getAll();

    @POST("carpeta/")
    Single<Carpeta> post(@Body Carpeta carpeta);

    @GET("carpeta/desbloquear/")
    Single<String> desbloquear(@Query("idCarpeta") int idCarpeta, @Query("pass") String pass);
}
