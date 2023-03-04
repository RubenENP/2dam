package services.impl;

import dao.ReadArticleDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ReadArticle;
import services.ServicesReadArticle;

import java.util.Map;

public class ServicesReadArticleImpl implements ServicesReadArticle {
    private final ReadArticleDao readArticleDao;

    @Inject
    public ServicesReadArticleImpl(ReadArticleDao readArticleDao) {
        this.readArticleDao = readArticleDao;
    }

    public Either<String, ReadArticle> add(ReadArticle readArticle){
        return readArticleDao.add(readArticle);
    }

    public Map<Double, String> getAvgRating(int reader){
        return  readArticleDao.getAvgRating(reader);
    }
}
