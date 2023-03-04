package servicios.impl;

import dao.ArticlesDao;
import domain.model.Article;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.ArticlesServicios;

import java.util.List;


public class ArticlesServiciosImpl implements ArticlesServicios {
    private final ArticlesDao articlesDao;

    @Inject
    public ArticlesServiciosImpl(ArticlesDao articlesDao) {
        this.articlesDao = articlesDao;
    }

    public Single<Either<String, List<Article>>> getAllArticles(){
        return articlesDao.getAllArticles();
    }

    public Single<Either<String, List<Article>>> getArticleByName(String name){return articlesDao.getArticlesByName(name);}

    public Single<Either<String, Article>> addArticle(String name, String description, int typeId, int newspaperId){

        Article article = new Article(0, name, description,typeId, newspaperId);

        return articlesDao.addArticle(article);
    }
}
