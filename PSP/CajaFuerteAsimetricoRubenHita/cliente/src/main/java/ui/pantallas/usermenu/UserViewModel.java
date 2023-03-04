package ui.pantallas.usermenu;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.Message;
import services.ServicesCarpetas;
import services.ServicesMensaje;

public class UserViewModel {
    private final ServicesCarpetas servicesCarpetas;
    private final ServicesMensaje servicesMensaje;

    @Inject
    public UserViewModel(ServicesCarpetas servicesCarpetas, ServicesMensaje servicesMensaje) {
        this.servicesCarpetas = servicesCarpetas;
        this.servicesMensaje = servicesMensaje;
        state = new SimpleObjectProperty<>();
    }

    private final ObjectProperty<UserState> state;

    public ReadOnlyObjectProperty<UserState> getState() {
        return state;
    }

    public void getAll() {
        servicesCarpetas.getAll()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    UserState userState;
                    if (either.isLeft()) {
                        userState = new UserState(null, null, null, null,null,
                                either.getLeft());
                    } else {
                        userState = new UserState(either.get(), null, null, null,null, null);
                    }

                    state.setValue(userState);
                });
    }

    public void postCarpeta(String name, int permiso, String pass) {
        servicesCarpetas.add(name, permiso, pass)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    UserState userState;
                    if (either.isLeft()) {
                        userState = new UserState(null, null, null, null,null, either.getLeft());
                    } else {
                        userState = new UserState(state.get().carpetaList(), either.get(), null,null, null, null);
                    }

                    state.setValue(userState);
                });
    }

    public void postMensaje(int carpetaId, String text) {
        servicesMensaje.postMensaje(carpetaId, text)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    UserState userState;
                    if (either.isLeft()) {
                        userState = new UserState(null, null, null, null, null,either.getLeft());
                    } else {
                        userState = new UserState(state.get().carpetaList(), null,
                                either.get(), null,null, null);
                    }

                    state.setValue(userState);
                });
    }

    public void getMessages(int idCarpeta) {
        servicesMensaje.getMensajes(idCarpeta)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    UserState userState;
                    if (either.isLeft()) {
                        userState = new UserState(null, null, null, null,null,
                                either.getLeft());
                    } else {
                        userState = new UserState(null, null, null, either.get(), null,null);
                    }

                    state.setValue(userState);
                });
    }

    public void desbloquearCarpeta(int carpetaId, String pass) {
        servicesCarpetas.desbloquearCarpeta(carpetaId, pass)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    UserState userState;
                    if (either.isLeft()) {
                        userState = new UserState(null, null, null, null,null,
                                either.getLeft());
                    } else {
                        userState = new UserState(null, null, null,null,
                                either.get(), null);
                    }

                    state.setValue(userState);
                });
    }

    public void borrarMensaje(Message message) {
        servicesMensaje.borrarMensaje(message)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    UserState userState;
                    if (either.isLeft()){
                        userState = new UserState(null, null, null, null,null,
                                either.getLeft());
                    } else {
                        userState = new UserState(null, null, null, null,"borrado.",
                                null);
                    }

                    state.setValue(userState);
                });
    }
}
