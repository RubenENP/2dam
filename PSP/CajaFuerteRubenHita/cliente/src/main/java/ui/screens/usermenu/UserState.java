package ui.screens.usermenu;

import modelo.Carpeta;
import modelo.Message;

import java.util.List;

public record UserState(List<Carpeta> carpetaList, Carpeta carpetaAnyadida, Message mensajeAnyadido,
                        List<Message> messageList, String status, String error) {
}
