package jakarta.security;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import modelo.User;
import services.ServicesUser;

import java.util.Collections;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

public class MyIdentityStore implements IdentityStore {

    private final ServicesUser servicesUser;

    @Inject
    MyIdentityStore(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
    }
    @Override
    public CredentialValidationResult validate(Credential credential) {
        CredentialValidationResult result;

        BasicAuthenticationCredential user = (BasicAuthenticationCredential) credential;

        String caller = user.getCaller();

        if (caller !=null){
            Either<String, User> usuario = servicesUser.comprobarUsuario(caller);

            if (usuario.isLeft()){
                result = INVALID_RESULT;
            } else {
                if (usuario.get().getRole().equals("admin")){
                    result = new CredentialValidationResult(usuario.get().getUsername(),
                            Collections.singleton(Constantes.ADMIN));
                } else {
                    result = new CredentialValidationResult(usuario.get().getUsername(),
                            Collections.singleton(Constantes.USER));
                }
            }

        } else {
            result = INVALID_RESULT;
        }

        return result;
    }
}
