package ui.pantallas.login;

import domain.model.Usuario;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import servicios.LoginServicios;

public class LoginViewModel {
    private final LoginServicios loginServicios;

    @Inject
    public LoginViewModel(LoginServicios loginServicios) {
        this.loginServicios = loginServicios;
        state = new SimpleObjectProperty<>(new Usuario(),null);
    }

    private final ObjectProperty<LoginState> state;

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void login(String user, String pass){
        loginServicios.login(user, pass)
                .observeOn(Schedulers.single())
                .subscribe(either ->{
                    LoginState loginState;

                    if (either.isLeft()){
                        loginState = new LoginState(null,null,null ,either.getLeft(), null);
                    } else {
                        loginState = new LoginState(either.get(), null,null,null, null);
                    }

                    state.setValue(loginState);
                });
    }

    public void register(String user, String pass, String email){
        loginServicios.register(user, pass, email)
                .observeOn(Schedulers.single())
                .subscribe(either ->{
                    LoginState loginState;

                    if (either.isLeft()){
                        loginState = new LoginState(null,null,null, either.getLeft(), null);
                    } else {
                        loginState = new LoginState(null, either.get(),null,null, null);
                    }

                    state.setValue(loginState);
                });
    }

    public void usuarioRecuperarField(String user){
        loginServicios.cambiarPass(user)
                .observeOn(Schedulers.single())
                .subscribe(either ->{
                   LoginState loginState;

                   if (either.isLeft()){
                       loginState = new LoginState(null,null,null, either.getLeft(), null);
                   } else {
                       loginState = new LoginState(null, null, either.get(), null, null);
                   }
                   state.setValue(loginState);
                });
    }

    public void logout() {
        loginServicios.logout()
                .observeOn(Schedulers.single())
                .subscribe(either ->{
                    LoginState loginState;

                    if (either.isLeft()){
                        loginState = new LoginState(null, null, null, either.getLeft(), null);
                    } else {
                        loginState = new LoginState(null, null, null, null, either.get());
                    }
                    state.setValue(loginState);
                });
    }
}
