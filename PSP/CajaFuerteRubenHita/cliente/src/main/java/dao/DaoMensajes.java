package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.Message;

import java.util.List;

public interface DaoMensajes {
    Single<Either<String, Message>> postMensaje(Message message);
    Single<Either<String, List<Message>>> getMensajes(int idCarpeta);

    Single<Either<String, Boolean>> delete(Message message);
}
