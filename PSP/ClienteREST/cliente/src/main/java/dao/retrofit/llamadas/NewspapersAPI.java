package dao.retrofit.llamadas;

import common.Constantes;
import domain.model.Newspaper;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface NewspapersAPI {

    @GET(Constantes.NEWSPAPERS)
    Single<List<Newspaper>> getNewspapers();

    @GET(Constantes.NEWSPAPERS_ID)
    Single<Newspaper> getUnNewspaper(@Path(Constantes.ID) int id);

    @POST(Constantes.NEWSPAPERS)
    Single<Newspaper> postNewspaper(@Body Newspaper newspaper);

    @DELETE(Constantes.NEWSPAPERS_ID)
    Single<Response<Object>> deleteNewspaper(@Path(Constantes.ID) int id);
}
