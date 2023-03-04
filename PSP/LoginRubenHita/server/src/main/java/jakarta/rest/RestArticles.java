package jakarta.rest;

import domain.errores.ApiError;
import domain.model.Article;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServicesArticles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Constantes.ARTICLES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("admin")
public class RestArticles {
    public static final String TYPE = "/{type}";
    private final ServicesArticles servicesArticles;

    @Inject
    public RestArticles(ServicesArticles servicesArticles) {
        this.servicesArticles = servicesArticles;
    }

    @GET
    public List<Article> getAllArticles() {
        return servicesArticles.getAllArticles();
    }

    @GET
    @Path(TYPE)
    public Response getUnArticle(@PathParam("type") String type){
        AtomicReference<Response> r = new AtomicReference<>();

        Either<List<Article>, String> articles = servicesArticles.getArticlesByType(type);


        if (articles.isLeft()){
            r.set(Response.ok().entity(articles.getLeft()).build());
        } else {
            ApiError apiError = new ApiError(articles.get(), LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return r.get();
    }

    @POST
    public Response addArticle(Article a){
        AtomicReference<Response> r = new AtomicReference<>();

        Either<Article, String> listArticle = servicesArticles.addArticle(a.getName_article(), a.getDescription(), a.getId_newspaper(), a.getId_type());

        if (listArticle.isLeft()){
            r.set(Response.ok().entity(listArticle.getLeft()).build());
        } else {
            ApiError apiError = new ApiError(listArticle.get(), LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return r.get();
    }
}
