package services.impl;

import dao.MessagesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.Encriptacion;
import modelo.Message;
import services.ServicesMessages;

import java.util.List;

public class ServicesMessagesImpl implements ServicesMessages {
    private final MessagesDao messagesDao;

    @Inject
    public ServicesMessagesImpl(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    @Override
    public Either<String, List<Message>> getAll(int idCarpeta) {
        return messagesDao.getAll(idCarpeta);
    }

    @Override
    public Either<String, Message> create(Message message) {
        return messagesDao.create(message);
    }

    @Override
    public Either<String, Message> update(Message message) {
        return messagesDao.update(message);
    }

    @Override
    public Either<String, Message> delete(int id) {
        return messagesDao.delete(id);
    }

    @Override
    public boolean comprobarAcceso(int idCarpeta, String usuario){
        return messagesDao.comprobarAcceso(idCarpeta, usuario);
    }
}
