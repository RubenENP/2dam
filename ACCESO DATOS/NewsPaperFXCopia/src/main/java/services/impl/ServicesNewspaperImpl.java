package services.impl;

import dao.DaoArticles;
import dao.DaoNewspaper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Article;
import model.Newspaper;
import services.ServicesNewspaper;

import java.io.IOException;
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

    public List<Newspaper> getAll() {
        List<Newspaper> response = new ArrayList<>();
        try {
            response = daoNewspaper.getAll();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public boolean addNewspaper(String id, String nombre, String precio, String director){
        return daoNewspaper.addNewspaper(id, nombre, precio, director);
    }

    public boolean deleteNewspaper(int id){
        List<Article> articleList = daoArticles.getAll();

        articleList.forEach(article -> {
            if(article.getIdNewspaper() == id){
                daoArticles.delete(article.getId());
            }
        });

        return daoNewspaper.delete(id);
    }

    public boolean checkArticles (int id){
        List<Article> articleList = daoArticles.getAll();
        return articleList.stream().filter(article -> article.getIdNewspaper() == id).findFirst().orElse(null) != null;
    }
}
