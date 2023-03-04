package services.impl;

import dao.DaoArticles;
import dao.DaoReadArticle;
import dao.DaoReaders;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import lombok.extern.log4j.Log4j2;
import model.Article;
import model.readArticle.ReadArticle;
import services.ServicesReadArticle;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    public Either<List<ReadArticle>, String> getAll() {
        return daoReadArticle.getAll();
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

    public ReadArticle update(ReadArticle readArticle, int rate) {
        return daoReadArticle.update(readArticle, rate);
    }
}
