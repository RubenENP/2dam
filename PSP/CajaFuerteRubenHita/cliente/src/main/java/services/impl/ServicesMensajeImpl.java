package services.impl;

import dao.DaoMensajes;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.Message;
import services.ServicesMensaje;

import java.util.List;

public class ServicesMensajeImpl implements ServicesMensaje {
    private final DaoMensajes daoMensajes;

    @Inject
    public ServicesMensajeImpl(DaoMensajes daoMensajes) {
        this.daoMensajes = daoMensajes;
    }

    @Override
    public Single<Either<String, Message>> postMensaje(int idCarpeta, String text) {
        return daoMensajes.postMensaje(new Message(0, idCarpeta, text));
    }

    @Override
    public Single<Either<String, List<Message>>> getMensajes(int idCarpeta) {
        return daoMensajes.getMensajes(idCarpeta);
    }

    @Override
    public Single<Either<String, Boolean>> borrarMensaje(Message message) {
        return daoMensajes.delete(message);
    }


}
