package retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SecurityAPI {
    @GET("basic/")
    Single<String> getServerPublicKey();

    @GET("basic/")
    Single<String> comprobarClave(@Header("Public-Key") String clavePublica);
}
