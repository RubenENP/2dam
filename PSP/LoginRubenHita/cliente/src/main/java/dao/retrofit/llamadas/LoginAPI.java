package dao.retrofit.llamadas;

import com.azure.core.annotation.PathParam;
import domain.model.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

public interface LoginAPI {
    @POST("privado/login/")
    Single<Usuario> login(@Header("Authorization") String header);

    @POST("privado/register/")
    Single<Usuario> register(@Body Usuario usuario);

    @GET("privado/newpass")
    Single<String> cambiarPass(@Query("user") String user);

    @POST("privado/logout")
    Single<String> logout(@Header("Logout") String logout);
}
