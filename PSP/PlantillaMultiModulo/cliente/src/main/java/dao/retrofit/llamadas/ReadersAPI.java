package dao.retrofit.llamadas;

import common.Constantes;
import domain.model.reader.Reader;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.*;

import java.util.List;

public interface ReadersAPI {

    @GET(Constantes.READERS)
    Single<List<Reader>> getAllReaders();

    @GET(Constantes.READERS_ID)
    Single<Reader> getUnReader(@Path(Constantes.ID) int id);

    @POST(Constantes.READERS)
    Single<Reader> addReader(@Body Reader reader);
    @PUT(Constantes.READERS)
    Single<Reader> updateReader(@Body Reader reader);
}
