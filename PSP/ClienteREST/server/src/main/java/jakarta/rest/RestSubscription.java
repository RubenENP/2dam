package jakarta.rest;

import domain.errores.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServicesSubscriptions;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

@Path(Constantes.SUBSCRIPTIONS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestSubscription {
    private final ServicesSubscriptions servicesSubscriptions;

    @Inject
    public RestSubscription(ServicesSubscriptions servicesSubscriptions) {
        this.servicesSubscriptions = servicesSubscriptions;
    }

    @GET
    @Path(Constantes.ID1)
    public Response getUnSubscription(@PathParam(Constantes.ID) int id){
        AtomicReference<Response> r = new AtomicReference<>();

        if (servicesSubscriptions.getAll(id).isEmpty()){
            ApiError apiError = new ApiError(Constantes.NO_SUBSCRIPTION_FOUND, LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        } else {
            r.set(Response.ok().entity(servicesSubscriptions.getAll(id)).build());
        }

        return r.get();
    }
}
