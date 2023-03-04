package servicios.impl;

import dao.DaoArticles;
import dao.DaoNewspapers;
import domain.model.Article;
import domain.model.Newspaper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servicios.ServicesNewspaper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class ServicesNewspaperImpl implements ServicesNewspaper {
    DaoNewspapers daoNewspaper;
    DaoArticles daoArticles;

    @Inject
    ServicesNewspaperImpl(DaoNewspapers daoNewspaper, DaoArticles daoArticles){
        this.daoNewspaper = daoNewspaper;
        this.daoArticles = daoArticles;
    }

    public List<Newspaper> getAll() {
        return daoNewspaper.getAll();
    }

    public Newspaper addNewspaper(String name, LocalDate releaseDate){
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
