package jakarta.rest;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.ApiError;
import services.ServicesSecurity;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Properties;

@Path("/security")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SecurityRest {
    private final ServicesSecurity servicesSecurity;

    @Inject
    public SecurityRest(ServicesSecurity servicesSecurity) {
        this.servicesSecurity = servicesSecurity;
    }

    @GET
    public Response get() {
        return Response.ok().build();
    }

    @POST
    public Response postClavePublica(@HeaderParam(Constantes.PUBLIC_KEY) String clavePublicaCliente) {
        if (clavePublicaCliente != null) {
            try {
                Properties p = new Properties();
                p.load(getClass().getResourceAsStream("/properties.yaml"));
                String password = (String) p.get("passwordClave");
                KeyStore ks = KeyStore.getInstance("PKCS12");
                ks.load(getClass().getResourceAsStream("/keystore.pfx"), password.toCharArray());

                Either<String, X509Certificate> cert = servicesSecurity.crearCertificado(clavePublicaCliente, ks, password);
                if (cert.isLeft()) {
                    return Response.status(Response.Status.BAD_REQUEST).entity(new ApiError(cert.getLeft(),
                            LocalDateTime.now())).build();
                } else {
                    return Response.ok(Base64.getUrlEncoder().encodeToString(cert.get().getEncoded())).build();
                }

            } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiError("Error clave publica",
                    LocalDateTime.now())).build();
        }
    }
}
