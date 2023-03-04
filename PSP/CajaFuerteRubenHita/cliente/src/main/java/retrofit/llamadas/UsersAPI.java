package retrofit.llamadas;

import com.azure.core.annotation.Get;
import io.reactivex.rxjava3.core.Single;
import modelo.User;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface UsersAPI {
    @POST("users/register/")
    Single<User> postUser(@Body User user);

    @POST("users/logout/")
    Single<String> logout(@Header("logout") String logout);

    @POST("users/login/")
    Single<User> login(@Header("Authorization") String header);

    @GET("users/")
    Single<List<User>> getUsers();
}
