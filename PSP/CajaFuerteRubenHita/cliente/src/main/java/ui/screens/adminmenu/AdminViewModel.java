package ui.screens.adminmenu;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modelo.User;
import services.ServicesUser;
import ui.screens.principal.PrincipalState;

public class AdminViewModel {
    private final ServicesUser servicesUser;

    private final ObjectProperty<AdminState> state;

    public ReadOnlyObjectProperty<AdminState> getState() {
        return state;
    }

    @Inject
    public AdminViewModel(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
        state = new SimpleObjectProperty<>(null, null);
    }

    public void postUser(User u) {
        servicesUser.postUser(u)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    AdminState adminState;
                    if (either.isLeft()){
                        adminState = new AdminState(null,null, either.getLeft());
                    } else {
                        adminState = new AdminState(either.get(), state.get().userList(), null);
                    }

                    state.setValue(adminState);
                });
    }


    public void getAllUsers() {
        servicesUser.getAll()
                .observeOn(Schedulers.single())
                .subscribe(either ->{
                    AdminState adminState;
                    if (either.isLeft()){
                        adminState = new AdminState(null,null, either.getLeft());
                    } else {
                        adminState = new AdminState(null,either.get(), null);
                    }

                    state.setValue(adminState);
                });
    }
}
