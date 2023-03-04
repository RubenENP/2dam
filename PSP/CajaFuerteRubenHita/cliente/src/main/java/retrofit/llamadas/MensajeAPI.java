package retrofit.llamadas;

import io.reactivex.rxjava3.core.Single;
import modelo.Message;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface MensajeAPI {
    @POST("mensajes/")
    Single<Message> postMessage(@Body Message message);

    @GET("mensajes/{idCarpeta}/")
    Single<List<Message>> getMessages(@Path("idCarpeta") int idcarpeta);

    @DELETE("mensajes/{id}")
    Single<Response<Void>> delete(@Path("id") int id);
}
