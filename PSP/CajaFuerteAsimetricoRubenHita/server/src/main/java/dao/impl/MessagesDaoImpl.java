package dao.impl;

import config.ConfigurationPool;
import dao.MessagesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import encriptacion.Encriptacion;
import mappers.CarpetaMapper;
import mappers.MessageMapper;
import mappers.RelacionUserCarpetaMapper;
import modelo.Carpeta;
import modelo.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import serverModel.RelacionUserCarpeta;

import java.util.List;

public class MessagesDaoImpl implements MessagesDao {
    JdbcTemplate jtm;

    ConfigurationPool pool;
    private final Encriptacion encriptacion;

    @Inject
    public MessagesDaoImpl(ConfigurationPool pool, Encriptacion encriptacion) {
        this.pool = pool;
        this.encriptacion = encriptacion;
        this.jtm = new JdbcTemplate(pool.getDataSource());
    }

    @Override
    public Either<String, List<Message>> getAll(int idCarpeta) {
        Either<String, List<Message>> response;
        String query = "SELECT * FROM mensajes where id_carpeta=?";

        Carpeta carpeta = jtm.query("SELECT * from carpetas WHERE id=?", new CarpetaMapper(), idCarpeta)
                .stream().findFirst().orElse(null);

        try {
            List<Message> messageList = jtm.query(query, new MessageMapper(), idCarpeta).stream().toList();

            messageList.forEach(message -> {
                assert carpeta != null;
                message.setText(encriptacion.desencriptar(message.getText(), carpeta.getPassword()));
            });

            response = Either.right(messageList);
        } catch (Exception e){
            response = Either.left(e.getMessage());
        }

        return response;
    }

    @Override
    public Either<String, Message> create(Message message) {
        Either<String, Message> response;

        Carpeta carpeta = jtm.query("SELECT * from carpetas WHERE id=?", new CarpetaMapper(),message.getIdCarpeta())
                .stream().findFirst().orElse(null);

        if (carpeta != null){
            String textoEncriptado = encriptacion.encriptar(message.getText(), carpeta.getPassword());

            String query = "insert into mensajes (id, id_carpeta, text) values (?, ?, ?)";
            try {
                jtm.update(query, message.getId(), message.getIdCarpeta(), textoEncriptado);
                response = Either.right(message);
            } catch (Exception e){
                response = Either.left(e.getMessage());
            }
        } else {
            response = Either.left("Selecciona una carpeta");
        }

        return response;
    }

    @Override
    public Either<String, Message> update(Message message) {
        Either<String, Message> response;
        String query = "update mensajes SET text = ? WHERE id = ?";
        try {
            jtm.update(query, message.getText(), message.getId());
            response = Either.right(message);
        } catch (Exception e){
            response = Either.left(e.getMessage());
        }
        return response;
    }

    @Override
    public Either<String, Message> delete(int id) {
        Either<String, Message> response;
        String query = "delete from mensajes where id=?";
        try {
            jtm.update(query, id);
            Message m = new Message();
            m.setId(id);
            response = Either.right(m);
        } catch (Exception e){
            response = Either.left(e.getMessage());
        }
        return response;
    }

    @Override
    public boolean comprobarAcceso(int idCarpeta, String usuario) {
        Carpeta carpeta = jtm.query("SELECT * FROM carpetas WHERE id = ?", new CarpetaMapper(), idCarpeta).stream().findFirst().orElse(null);

        assert carpeta != null;
        if (carpeta.getUsername().equals(usuario)){
            return true;
        } else {
            List<RelacionUserCarpeta> r = jtm.query("SELECT * FROM relacionUserCarpeta WHERE user = ? AND id_carpeta = ?", new RelacionUserCarpetaMapper(), usuario, idCarpeta);
            return !r.isEmpty();
        }
    }
}
