package servicios.impl;

import dao.DaoArticles;
import dao.DaoReadArticle;
import dao.DaoReaders;
import domain.model.Article;
import domain.model.readArticle.ReadArticle;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servicios.ServicesReadArticle;

import java.util.List;

@Log4j2
public class ServicesReadArticleImpl implements ServicesReadArticle {
    DaoReadArticle daoReadArticle;
    DaoReaders daoReaders;
    DaoArticles daoArticles;

    @Inject
    ServicesReadArticleImpl(DaoReadArticle daoReadArticle, DaoReaders daoReaders, DaoArticles daoArticles) {
        this.daoReadArticle = daoReadArticle;
        this.daoReaders = daoReaders;
        this.daoArticles = daoArticles;
    }

    public List<ReadArticle> getAll() {
        return daoReadArticle.getAll().getLeft();
    }

    public int addReadArticle(int idReader, String articleName, int rating) {
        List<Article> allArticles = daoArticles.getAll();

        int response = 0;

        Article article = allArticles.stream().filter(article1 -> article1.getName_article().equals(articleName)).findFirst().orElse(null);
        if (article != null) {
            ReadArticle readArticle = new ReadArticle(idReader, article.getId(), rating);
            return daoReadArticle.save(readArticle);
        }

        return response;
    }
}
