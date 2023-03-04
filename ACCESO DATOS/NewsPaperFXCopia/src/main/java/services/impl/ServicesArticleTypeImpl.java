package services.impl;

import dao.DaoArticlesType;
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

    public List<ArticleType> getAllTypes(){
        return daoArticlesType.getAll();
    }

    public List<String> getAllNameTypes(){
        return daoArticlesType.getAll().stream().map(ArticleType::getName).toList();
    }
}

