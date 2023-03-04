package ui.pantallas.login;

import domain.model.Usuario;

public record LoginState (Usuario usuarioLogin, Usuario usuarioRegister, String cambiarPass, String error, String logout){}
