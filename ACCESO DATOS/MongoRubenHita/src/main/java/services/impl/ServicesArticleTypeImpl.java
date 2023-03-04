package services.impl;

import dao.ArticleTypeHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import services.ServicesArticleType;

import java.util.List;

public class ServicesArticleTypeImpl implements ServicesArticleType {
    private final ArticleTypeHibDao articleTypeHibDao;

    @Inject
    public ServicesArticleTypeImpl(ArticleTypeHibDao articleTypeHibDao) {
        this.articleTypeHibDao = articleTypeHibDao;
    }

//    public Either<String, List<ArticleType>> getAll() {
//        return articleTypeHibDao.getAll();
//    }
//
//    public Either<String, ArticleTypeHib> getReadestArticleType(){
//        Either<String, ArticleTypeHib> response = articleTypeHibDao.getReadestArticleType();
//
//        if (response.isRight() && response.get() == null){
//            response = Either.left("NO ARTICLE TYPES");
//        }
//
//        return response;
//    }
}
