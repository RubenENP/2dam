package jakarta.rest;

import domain.model.readArticle.ReadArticle;
import domain.errores.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServicesReadArticle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Constantes.READARTICLE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestReadArticle {
    private final ServicesReadArticle servicesReadArticle;

    @Inject
    public RestReadArticle(ServicesReadArticle servicesReadArticle) {
        this.servicesReadArticle = servicesReadArticle;
    }

    @GET
    public List<ReadArticle> getAllReadArticle(){return servicesReadArticle.getAll();}

    @GET
    @Path(Constantes.ID1)
    public Response getUnReadArticle(@PathParam(Constantes.ID) int id){
        AtomicReference<Response> r = new AtomicReference<>();

        servicesReadArticle.getAll().forEach(readArticle -> {
            if (readArticle.getId() == id){
                r.set(Response.ok().entity(readArticle).build());
            }
        });

        if (r.get() == null){
            ApiError apiError = new ApiError(Constantes.NO_READ_ARTICLE_FOUND, LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return r.get();
    }
}
