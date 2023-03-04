package servicios.impl;

import dao.DaoLogin;
import domain.model.Activacion;
import domain.model.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.ServiciosLogin;

public class ServiciosLoginImpl implements ServiciosLogin {
    private final DaoLogin daoLogin;

    @Inject
    public ServiciosLoginImpl(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public Either<String, Usuario> login(String user, String pass) {
        return daoLogin.login(user, pass);
    }

    public Either<String, Usuario> register(Usuario usuario) {
        Either<String, Usuario> respuesta;

        if (usuario.getPassword().length() < 8) {
            respuesta = Either.left("La contraseña debe superar los 8 caracteres");
        } else {
            respuesta = daoLogin.register(usuario);
        }
        return respuesta;
    }

    public Either<String, Activacion> activarUsuario(String codigo) {
        return daoLogin.activarUsuario(codigo);
    }

    public Either<String, Usuario> comprobarUsuario(String user) {
        return daoLogin.comprobarUsuario(user);
    }

    public Either<String, Usuario> cambiarPassword(String nombreUsuario, String newPassword) {
        return daoLogin.cambiarPassword(nombreUsuario, newPassword);
    }
}
