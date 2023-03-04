package jakarta.rest;

import domain.model.reader.Reader;
import domain.errores.ApiError;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import servicios.ServicesReaders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Constantes.READERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("user")
public class RestReaders {
    @Context
    SecurityContext securityContext;

    private final ServicesReaders servicesReaders;

    @Inject
    public RestReaders(ServicesReaders servicesReaders) {
        this.servicesReaders = servicesReaders;
    }

    @GET
    public List<Reader> readerList(){return servicesReaders.getAll();}

    @GET
    @Path(Constantes.ID1)
    public Response getUnReader(@PathParam(Constantes.ID) int id){
        AtomicReference<Response> r = new AtomicReference<>();

        Either<String, Reader> reader = servicesReaders.get(id, securityContext.getUserPrincipal().getName());

        if (reader.isLeft()){
            ApiError apiError = new ApiError(reader.getLeft(), LocalDateTime.now());
            r.set(Response.status(Response.Status.CONFLICT).entity(apiError).build());
        } else {
            r.set(Response.ok().entity(reader.get()).build());
        }

        return r.get();
    }

    @POST
    public Response addReader(Reader r){
        Either<String, Reader> addedReader = servicesReaders.addReader(r.getId(), r.getName(), r.getBirthDate(),
                securityContext.getUserPrincipal().getName());

        AtomicReference<Response> response = new AtomicReference<>();

        if (addedReader.isRight()){
            response.set(Response.ok().entity(addedReader.get()).build());
        } else {
            ApiError apiError = new ApiError(addedReader.getLeft(), LocalDateTime.now());
            response.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return response.get();
    }

    @PUT
    public Response updateReader(Reader r){
        AtomicReference<Response> response = new AtomicReference<>();

        int update = servicesReaders.update(r.getName(), r.getId(), r.getBirthDate());

        if (update == 1){
            response.set(Response.ok().entity(r).build());
        } else {
            ApiError apiError = new ApiError(Constantes.ERROR, LocalDateTime.now());
            response.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return response.get();
    }
}
