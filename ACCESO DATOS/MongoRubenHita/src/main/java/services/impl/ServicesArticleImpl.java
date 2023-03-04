package services.impl;

import dao.ArticleHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import model.Newspaper;
import services.ServicesArticle;

import java.util.List;

public class ServicesArticleImpl implements ServicesArticle {
    private final ArticleHibDao articleHibDao;

    @Inject
    public ServicesArticleImpl(ArticleHibDao articleHibDao) {
        this.articleHibDao = articleHibDao;
    }

    @Override
    public Either<String, String> delete(Newspaper newspaper) {
        int deleted = articleHibDao.delete(newspaper);
        if (deleted == 404){
            return Either.left("The newspaper dont exist");
        } else {
            return Either.right("All articles from " +newspaper.getName()+" deleted.");
        }
    }

//    public Either<String, List<Article>> getAll() {
//        return articleHibDao.getAll();
//    }

    public Either<String, List<Article>> getAll(Newspaper newspaper){
        return articleHibDao.getAll(newspaper);
    }
}
