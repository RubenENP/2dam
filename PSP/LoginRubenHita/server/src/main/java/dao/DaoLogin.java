package dao;

import domain.model.Activacion;
import domain.model.Usuario;
import io.vavr.control.Either;

public interface DaoLogin {
    Either<String, Usuario> login(String user, String pass);
    Either<String, Usuario> register(Usuario usuario);
    Either<String, Activacion> activarUsuario(String codigo);
    Either<String, Usuario> comprobarUsuario(String user);
    Either<String, Usuario> cambiarPassword(String nombreUsuario, String newPassword);
}
