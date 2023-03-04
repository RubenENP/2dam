package dao.retrofit.llamadas;

import common.Constantes;
import domain.model.ArticleType;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

import java.util.List;

public interface ArticleTypeAPI {

    @GET(Constantes.TYPE)
    Single<List<ArticleType>> getAllArticleType();

}
