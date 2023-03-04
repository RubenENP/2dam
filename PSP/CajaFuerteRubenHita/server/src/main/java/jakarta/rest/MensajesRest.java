package jakarta.rest;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import modelo.ApiError;
import modelo.Carpeta;
import modelo.Message;
import services.ServicesCarpeta;
import services.ServicesMessages;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/mensajes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensajesRest {
    @Context
    SecurityContext securityContext;

    private final ServicesMessages servicesMessages;
    private final ServicesCarpeta servicesCarpeta;
    AtomicReference<Response> r;

    @Inject
    public MensajesRest(ServicesMessages servicesMessages, ServicesCarpeta servicesCarpeta) {
        this.servicesMessages = servicesMessages;
        this.servicesCarpeta = servicesCarpeta;
        r = new AtomicReference<>();
    }

    @GET
    @Path("/{idCarpeta}")
    public Response getAll(@PathParam("idCarpeta") int idCarpeta) {

        boolean comprobarAcceso = servicesMessages.comprobarAcceso(idCarpeta, securityContext.getUserPrincipal().getName());
        if (comprobarAcceso) {
            Either<String, List<Message>> either = servicesMessages.getAll(idCarpeta);

            if (either.isLeft()) {
                r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(either.getLeft(), LocalDateTime.now())).build());
            } else {
                r.set(Response.ok().entity(either.get()).build());
            }
        } else {
            r.set(Response.status(Response.Status.FORBIDDEN).entity(new ApiError(Constantes.NO_TIENES_ACCESO, LocalDateTime.now()))
                    .build());
        }

        return r.get();
    }

    @POST
    public Response post(Message message) {
        Either<String, Carpeta> carpeta = servicesCarpeta.get(message.getIdCarpeta());

        if (carpeta.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(carpeta.getLeft(), LocalDateTime.now())).build());
        } else {
            boolean comprobarAcceso = servicesMessages.comprobarAcceso(message.getIdCarpeta(),
                    securityContext.getUserPrincipal().getName());
            if (!comprobarAcceso || carpeta.get().getPermission() == 1) {
                r.set(Response.status(Response.Status.CONFLICT)
                        .entity(new ApiError(Constantes.NO_TIENES_ACCESO, LocalDateTime.now())).build());
            } else {
                Either<String, Message> either = servicesMessages.create(message);

                if (either.isLeft()) {
                    r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(either.getLeft(), LocalDateTime.now())).build());
                } else {
                    r.set(Response.ok().entity(either.get()).build());
                }
            }
        }
        return r.get();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        Either<String, Carpeta> carpeta = servicesCarpeta.get(id);

        if (carpeta.isLeft()) {
            r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(carpeta.getLeft(), LocalDateTime.now())).build());
        } else {
            boolean comprobarAcceso = servicesMessages.comprobarAcceso(id,
                    securityContext.getUserPrincipal().getName());
            if (!comprobarAcceso || carpeta.get().getPermission() == 1) {
                r.set(Response.status(Response.Status.CONFLICT)
                        .entity(new ApiError(Constantes.NO_TIENES_ACCESO, LocalDateTime.now())).build());
            } else {
                Either<String, Message> delete = servicesMessages.delete(id);

                if (delete.isLeft()) {
                    r.set(Response.status(Response.Status.CONFLICT).entity(new ApiError(delete.getLeft(), LocalDateTime.now())).build());
                } else {
                    r.set(Response.ok().entity(delete.get()).build());
                }
            }
        }
        return r.get();
    }
}
