package jakarta.rest;

import domain.model.Newspaper;
import domain.errores.ApiError;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Constantes.NEWSPAPERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestNewspaper {
    private final servicios.ServicesNewspaper serviciosNewspaper;

    @Inject
    public RestNewspaper(servicios.ServicesNewspaper serviciosNewspaper) {
        this.serviciosNewspaper = serviciosNewspaper;
    }

    @GET
    public List<Newspaper> getAllNewspaper() {
        return serviciosNewspaper.getAll();
    }

    @GET
    @Path(Constantes.ID1)
    @RolesAllowed("admin")
    public Response getUnNewspaper(@PathParam(Constantes.ID) int id){
        AtomicReference<Response> r = new AtomicReference<>();

        serviciosNewspaper.getAll().forEach(newspaper -> {
            if (newspaper.getId() == id){
                r.set(Response.ok().entity(newspaper).build());
            }
        });

        if (r.get() == null){
            ApiError apiError = new ApiError(Constantes.NO_NEWSPAPER_FOUND, LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return r.get();
    }

    @POST

    @RolesAllowed("admin")
    public Newspaper addNewspaper(Newspaper newspaper){
        return serviciosNewspaper.addNewspaper(newspaper.getName_newspaper(), newspaper.getRelease_date());
    }

    @DELETE
    @Path(Constantes.ID1)
    @RolesAllowed("admin")
    public Response deleteNewspaper(@PathParam(Constantes.ID) int id){
        AtomicReference<Response> r = new AtomicReference<>();

        Newspaper n = serviciosNewspaper.deleteNewspaper(id);

        if (n==null){
            ApiError apiError = new ApiError(Constantes.NO_NEWSPAPER_FOUND, LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        } else {
            r.set(Response.ok().build());
        }

        return r.get();
    }
}
