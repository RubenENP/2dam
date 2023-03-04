package dao.impl;

import com.google.gson.Gson;
import dao.ArticlesDao;
import dao.DaoGenerics;
import dao.retrofit.llamadas.ArticlesAPI;
import domain.model.Article;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ArticlesDaoImpl extends DaoGenerics implements ArticlesDao {
    private final ArticlesAPI articlesAPI;

    @Inject
    ArticlesDaoImpl(ArticlesAPI articlesAPI, Gson gson) {
         super(gson);
         this.articlesAPI = articlesAPI;
    }

    public Single<Either<String, List<Article>>> getAllArticles(){
        return safeAPICall(articlesAPI.getArticles());
    }

    public Single<Either<String, List<Article>>> getArticlesByName(String name){
        return safeAPICall(articlesAPI.getUnArticle(name));
    }

    public Single<Either<String, Article>> addArticle(Article article){
        return safeAPICall(articlesAPI.postArticle(article));
    }
}
