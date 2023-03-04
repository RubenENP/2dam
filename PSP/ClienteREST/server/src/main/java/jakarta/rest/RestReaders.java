package jakarta.rest;

import domain.model.reader.Reader;
import domain.errores.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.ServicesReaders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(Constantes.READERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestReaders {
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

        servicesReaders.getAll().forEach(reader -> {
            if (reader.getId() == id){
                r.set(Response.ok().entity(reader).build());
            }
        });

        if (r.get() == null){
            ApiError apiError = new ApiError(Constantes.NO_READER_FOUND, LocalDateTime.now());
            r.set(Response.status(Response.Status.NOT_FOUND).entity(apiError).build());
        }

        return r.get();
    }

    @POST
    public Response addReader(Reader r){
        int addedReader = servicesReaders.addReader(r.getId(), r.getName(), r.getBirthDate());

        AtomicReference<Response> response = new AtomicReference<>();

        if (addedReader == 1){
            response.set(Response.ok().entity(r).build());
        } else {
            ApiError apiError = new ApiError(Constantes.ERROR, LocalDateTime.now());
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
