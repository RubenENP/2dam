package jakarta.rest;

import domain.model.ArticleType;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import servicios.ServicesArticleType;

import java.util.List;

@Path("/type")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestType {
    private final ServicesArticleType servicesArticleType;

    @Inject
    RestType(ServicesArticleType servicesArticleType){
        this.servicesArticleType = servicesArticleType;
    }

    @GET
    public List<ArticleType> articleTypeList(){return servicesArticleType.getAllTypes().getLeft();}
}
