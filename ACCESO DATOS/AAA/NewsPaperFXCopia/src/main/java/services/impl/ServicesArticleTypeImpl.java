package services.impl;

import dao.DaoArticlesType;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ArticleType;
import services.ServicesArticleType;

import java.util.List;

public class ServicesArticleTypeImpl implements ServicesArticleType {
    DaoArticlesType daoArticlesType;

    @Inject
    ServicesArticleTypeImpl(DaoArticlesType daoArticlesType){
        this.daoArticlesType = daoArticlesType;
    }

    public Either<List<ArticleType>, String> getAllTypes(){
        Either<List<ArticleType>, String> response;

        if (daoArticlesType.getAll().isLeft()){
            response = Either.left(daoArticlesType.getAll().getLeft());
        } else {
            response = Either.right(daoArticlesType.getAll().get());
        }

        return response;
    }

    public Either<List<String>, String> getAllNameTypes(){
        Either<List<String>, String> response;

        if (daoArticlesType.getAll().isLeft()){
            response = Either.left(daoArticlesType.getAll().getLeft().stream().map(ArticleType::getName).toList());
        } else {
            response = Either.right(daoArticlesType.getAll().get());
        }

        return response;
    }
}

