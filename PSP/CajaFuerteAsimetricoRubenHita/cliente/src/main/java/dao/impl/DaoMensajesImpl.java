package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.DaoMensajes;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.Message;
import retrofit.llamadas.MensajeAPI;

import java.util.List;

public class DaoMensajesImpl extends DaoGenerics implements DaoMensajes {
    private final MensajeAPI mensajeAPI;
    @Inject
    public DaoMensajesImpl(Gson gson, MensajeAPI mensajeAPI) {
        super(gson);
        this.mensajeAPI = mensajeAPI;
    }


    @Override
    public Single<Either<String, Message>> postMensaje(Message message) {
        return safeAPICall(mensajeAPI.postMessage(message));
    }

    @Override
    public Single<Either<String, List<Message>>> getMensajes(int idCarpeta) {
        return safeAPICall(mensajeAPI.getMessages(idCarpeta));
    }

    @Override
    public Single<Either<String, Boolean>> delete(Message message) {
        return safeAPICallToDelete(mensajeAPI.delete(message.getId()));
    }
}
