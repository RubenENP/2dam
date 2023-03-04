package jakarta.rest;

import domain.errores.ApiError;
import domain.model.Activacion;
import domain.model.Usuario;
import io.vavr.control.Either;
import jakarta.Utils;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServiciosLogin;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;

@Path("/privado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {
    private final ServiciosLogin serviciosLogin;

    @Inject
    public RestLogin(ServiciosLogin serviciosLogin) {
        this.serviciosLogin = serviciosLogin;
    }

    @POST
    @Path("/login")
    public Response login(@HeaderParam("Authorization") String header) {
        String[] s = header.split(" ");
        String userpass = new String(Base64.getUrlDecoder().decode(s[1]));
        String[] userpassSeparado = userpass.split(":");

        Either<String, Usuario> login = serviciosLogin.login(userpassSeparado[0], userpassSeparado[1]);
        AtomicReference<Response> r = new AtomicReference<>();

        if (login.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(login.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(login.get()).build());
        }

        return r.get();
    }

    @POST
    @Path("/register")
    public Response register(Usuario u) {
        AtomicReference<Response> response = new AtomicReference<>();

        try {
            Either<String, Usuario> registerServicios = serviciosLogin.register(u);

            if (registerServicios.isLeft()) {
                response.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(registerServicios.getLeft(), LocalDateTime.now())).build());
            } else {
                response.set(Response.ok().entity(registerServicios.get()).build());
            }
        } catch (Exception e) {
            response.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(e.getMessage(), LocalDateTime.now())).build());
        }

        return response.get();
    }

    @POST
    @Path("/logout")
    public Response Logout(@HeaderParam("Logout") String header){
        return Response.ok().entity(header).build();
    }
}
