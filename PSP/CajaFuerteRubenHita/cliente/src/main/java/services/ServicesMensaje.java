package services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.Message;

import java.util.List;

public interface ServicesMensaje {
    Single<Either<String, Message>> postMensaje(int idCarpeta, String text);

    Single<Either<String, List<Message>>> getMensajes(int idCarpeta);

    Single<Either<String, Boolean>> borrarMensaje(Message message);
}
