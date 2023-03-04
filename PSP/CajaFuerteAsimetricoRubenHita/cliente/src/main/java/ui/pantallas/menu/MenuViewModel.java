package ui.pantallas.menu;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.User;
import services.ServicesSecurity;
import services.ServicesUser;

import java.security.NoSuchAlgorithmException;

public class MenuViewModel {
    private final ServicesUser servicesUser;
    private final ServicesSecurity servicesSecurity;

    private final ObjectProperty<MenuState> state;

    public ReadOnlyObjectProperty<MenuState> getState() {
        return state;
    }

    @Inject
    public MenuViewModel(ServicesUser servicesUser, ServicesSecurity servicesSecurity) {
        this.servicesUser = servicesUser;
        this.servicesSecurity = servicesSecurity;
        state = new SimpleObjectProperty<>(null, null);
    }

    public void login(String user, String pass) throws NoSuchAlgorithmException {
        servicesUser.login(user, pass)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    MenuState menuState;
                    if (either.isLeft()){
                        menuState = new MenuState(null, null, either.getLeft());
                    } else {
                        menuState = new MenuState(either.get(), null, null);
                    }

                    state.setValue(menuState);
                });
    }

    public void register(String user, String password) {
        servicesUser.postUser(new User(user, password, "user"))
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    MenuState menuState;
                    if (either.isLeft()){
                        menuState = new MenuState(null, null, either.getLeft());
                    } else {
                        menuState = new MenuState(null, either.get(), null);
                    }

                    state.setValue(menuState);
                });
    }

    public void getServerCertificate() {
        servicesSecurity.getServerPublicKey()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    MenuState menuState;
                    if (either.isLeft()){
                        menuState = new MenuState(null, null, either.getLeft());
                    } else {
                        menuState = new MenuState(null, null, null);
                    }

                    state.setValue(menuState);
                });
    }
}
