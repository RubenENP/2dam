package jakarta.rest;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import modelo.ApiError;
import modelo.Carpeta;
import services.ServicesCarpeta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/carpeta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarpetasRest {
    private final ServicesCarpeta servicesCarpeta;

    @Context
    SecurityContext securityContext;

    @Inject
    public CarpetasRest(ServicesCarpeta servicesCarpeta) {
        this.servicesCarpeta = servicesCarpeta;
    }

    AtomicReference<Response> r = new AtomicReference<>();

    @POST
    public Response createCarpeta(Carpeta carpeta) {
        Either<String, Carpeta> either = servicesCarpeta.create(carpeta);

        if (either.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(
                    new ApiError(either.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(either.get()).build());
        }

        return r.get();
    }

    @GET
    @Path("/private")
    public Response getCarpetasOfAnUser() {
        String name = securityContext.getUserPrincipal().getName();
        Either<String, List<Carpeta>> either = servicesCarpeta.getAll(name);

        if (either.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(
                    new ApiError(either.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(either.get()).build());
        }

        return r.get();
    }

    @GET
    public Response getAll() {
        Either<String, List<Carpeta>> either = servicesCarpeta.getAll();

        if (either.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(
                    new ApiError(either.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(either.get()).build());
        }

        return r.get();
    }

    @GET
    @Path("/desbloquear")
    public Response desbloquearCarpeta(@QueryParam("idCarpeta") int idCarpeta, @QueryParam("pass") String pass){
        Either<String, String> either = servicesCarpeta.desbloquearCarpeta(idCarpeta, pass, securityContext.getUserPrincipal().getName());

        if (either.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(
                    new ApiError(either.getLeft(), LocalDateTime.now())).build());
        } else {
            r.set(Response.ok().entity(either.get()).build());
        }

        return r.get();
    }
}
