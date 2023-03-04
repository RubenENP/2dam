package servicios.impl;

import dao.ArticleTypeDao;
import domain.model.ArticleType;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.ArticleTypeServicios;

import java.util.List;

public class ArticleTypeServiciosImpl implements ArticleTypeServicios {
    private final ArticleTypeDao articleTypeDao;

    @Inject
    public ArticleTypeServiciosImpl(ArticleTypeDao articleTypeDao) {
        this.articleTypeDao = articleTypeDao;
    }

    public Single<Either<String, List<ArticleType>>> getAllArticleType(){return articleTypeDao.getAllArticleType();}
}
