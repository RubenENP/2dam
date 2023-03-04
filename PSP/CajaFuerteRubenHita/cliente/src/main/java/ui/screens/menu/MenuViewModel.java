package ui.screens.menu;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ServicesUser;
import ui.screens.principal.PrincipalState;

public class MenuViewModel {
    private final ServicesUser servicesUser;

    private final ObjectProperty<MenuState> state;

    public ReadOnlyObjectProperty<MenuState> getState() {
        return state;
    }

    @Inject
    public MenuViewModel(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
        state = new SimpleObjectProperty<>(null, null);
    }

    public void login(String user, String pass) {
        servicesUser.login(user, pass)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    MenuState menuState;
                    if (either.isLeft()){
                        menuState = new MenuState(null, either.getLeft());
                    } else {
                        menuState = new MenuState(either.get(), null);
                    }

                    state.setValue(menuState);
                });
    }
}
