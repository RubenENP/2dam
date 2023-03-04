package dao.retrofit.llamadas;

import common.Constantes;
import domain.model.Article;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ArticlesAPI {

    @GET(Constantes.ARTICLES)
    Single<List<Article>> getArticles();

    @GET(Constantes.ARTICLES_TYPE)
    Single<List<Article>> getUnArticle(@Path(Constantes.TYPE1) String name);

    @POST(Constantes.ARTICLES)
    Single<Article> postArticle(@Body Article article);
}
