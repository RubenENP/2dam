package jakarta.security;

import common.Constantes;
import domain.model.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import servicios.ServiciosLogin;

import java.util.Collections;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

public class MyIdentityStore implements IdentityStore {

    private final ServiciosLogin serviciosLogin;

    @Inject
    MyIdentityStore(ServiciosLogin serviciosLogin) {
        this.serviciosLogin = serviciosLogin;
    }
    @Override
    public CredentialValidationResult validate(Credential credential) {
        CredentialValidationResult result;

        BasicAuthenticationCredential user = (BasicAuthenticationCredential) credential;

        String caller = user.getCaller();

        if (caller !=null){
            Either<String, Usuario> usuario = serviciosLogin.comprobarUsuario(caller);

            if (usuario.isLeft()){
                result = INVALID_RESULT;
            } else {
                if (usuario.get().getRole().equals("admin")){
                    result = new CredentialValidationResult(usuario.get().getUser(),
                            Collections.singleton(Constantes.ADMIN));
                } else {
                    result = new CredentialValidationResult(usuario.get().getUser(),
                            Collections.singleton(Constantes.USER));
                }
            }

        } else {
            result = INVALID_RESULT;
        }

        return result;
    }
}
