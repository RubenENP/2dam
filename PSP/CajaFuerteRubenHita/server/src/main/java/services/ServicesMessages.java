package services;

import io.vavr.control.Either;
import modelo.Message;

import java.util.List;

public interface ServicesMessages {
    Either<String, List<Message>> getAll(int idCarpeta);
    Either<String, Message> create(Message message);
    Either<String, Message> update(Message message);
    Either<String, Message> delete(int id);
    boolean comprobarAcceso(int idCarpeta, String usuario);
}
