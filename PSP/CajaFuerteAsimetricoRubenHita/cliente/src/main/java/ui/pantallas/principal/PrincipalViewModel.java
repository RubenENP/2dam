package ui.pantallas.principal;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ServicesUser;

public class PrincipalViewModel {
    private final ServicesUser servicesUser;

    private final ObjectProperty<PrincipalState> state;

    public ReadOnlyObjectProperty<PrincipalState> getState() {
        return state;
    }

    @Inject
    public PrincipalViewModel(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
        state = new SimpleObjectProperty<>(null, null);
    }

    public void logout(String logout) {
        servicesUser.logout(logout)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    PrincipalState principalState;

                    if (either.isLeft()){
                        principalState = new PrincipalState(null, either.getLeft());
                    } else {
                        principalState = new PrincipalState(either.get(), null);
                    }

                    state.setValue(principalState);
                });
    }
}
