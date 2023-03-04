package services.impl;

import dao.DaoArticles;
import dao.DaoNewspaper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Article;
import model.Newspaper;
import services.ServicesNewspaper;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class ServicesNewspaperImpl implements ServicesNewspaper {
    DaoNewspaper daoNewspaper;
    DaoArticles daoArticles;

    @Inject
    ServicesNewspaperImpl(DaoNewspaper daoNewspaper, DaoArticles daoArticles){
        this.daoNewspaper = daoNewspaper;
        this.daoArticles = daoArticles;
    }

    public Either<List<Newspaper>, String> getAll() {
        Either<List<Newspaper>, String> response;
        List<Newspaper> newspapers = daoNewspaper.getAll();

        if(newspapers.isEmpty()){
            response = Either.right("Lista vacia");
        } else {
            response = Either.left(newspapers);
        }

        return response;
    }

    public Newspaper addNewspaper(String name, Date releaseDate){
        Newspaper n = new Newspaper(name, releaseDate);
        return daoNewspaper.addNewspaper(n);
    }

    public Newspaper deleteNewspaper(int id){
        Newspaper n = new Newspaper(id);

        return daoNewspaper.delete(n);
    }

    public boolean checkArticles (int id){
        List<Article> articleList = daoArticles.getAll();
        return articleList.stream().filter(article -> article.getId_newspaper() == id).findFirst().orElse(null) != null;
    }
}
