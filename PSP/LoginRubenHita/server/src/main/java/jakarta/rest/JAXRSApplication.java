package jakarta.rest;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath(Constantes.API)
@DeclareRoles({"admin", "user"})
public class JAXRSApplication extends Application {

}
