package dao.impl;

import com.google.gson.Gson;
import dao.ArticleTypeDao;
import dao.DaoGenerics;
import dao.retrofit.llamadas.ArticleTypeAPI;
import domain.model.ArticleType;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ArticleTypeDaoImpl extends DaoGenerics implements ArticleTypeDao {
    private final ArticleTypeAPI articleTypeAPI;

    @Inject
    public ArticleTypeDaoImpl(ArticleTypeAPI articleTypeAPI, Gson gson) {
        super(gson);
        this.articleTypeAPI = articleTypeAPI;
    }

    public Single<Either<String, List<ArticleType>>> getAllArticleType(){
        return safeAPICall(articleTypeAPI.getAllArticleType());
    }
}
