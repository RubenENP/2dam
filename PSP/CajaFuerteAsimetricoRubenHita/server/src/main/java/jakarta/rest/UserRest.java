package jakarta.rest;

import claves.Claves;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.ApiError;
import modelo.User;
import org.bouncycastle.operator.OperatorCreationException;
import services.ServicesUser;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {
    private final ServicesUser servicesUser;
    private final Pbkdf2PasswordHash hash;

    @Inject
    public UserRest(ServicesUser servicesUser, Pbkdf2PasswordHash hash) {
        this.servicesUser = servicesUser;
        this.hash = hash;
    }

    @GET
    public List<User> getAll() {
        return servicesUser.getAll();
    }

    @POST
    @Path("/login")
    public Response login(@HeaderParam("Authorization") String header) {
        String[] s = header.split(" ");
        String userpass = new String(Base64.getUrlDecoder().decode(s[1]));
        String[] userpassSeparado = userpass.split(":");

        User u = new User(userpassSeparado[0], userpassSeparado[0], "user");

        Either<String, User> log = servicesUser.login(u);

        AtomicReference<Response> r = new AtomicReference<>();

        if (log.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(log.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(u).build());
        }

        return r.get();
    }

    @POST
    @Path("/register")
    public Response register(User u) {
        Either<String, User> reg = servicesUser.register(u);

        AtomicReference<Response> r = new AtomicReference<>();

        if (reg.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(reg.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(reg.get()).build());
        }

        return r.get();
    }

    @POST
    @Path("/logout")
    public Response logout(@HeaderParam("Logout") String header) {
        AtomicReference<Response> r = new AtomicReference<>();
        if (header.equals("logout")) {
            r.set(Response.ok().entity(header).build());
        } else {
            r.set(Response.status(Response.Status.CONFLICT).build());
        }

        return r.get();
    }
}
