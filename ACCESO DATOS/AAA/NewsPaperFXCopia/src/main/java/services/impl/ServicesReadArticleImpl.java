package services.impl;

import dao.DaoArticles;
import dao.DaoReadArticle;
import dao.DaoReaders;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import lombok.extern.log4j.Log4j2;
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
    ServicesReadArticleImpl(DaoReadArticle daoReadArticle, DaoReaders daoReaders, DaoArticles daoArticles){
        this.daoReadArticle = daoReadArticle;
        this.daoReaders = daoReaders;
        this.daoArticles = daoArticles;
    }

    public Either<List<ReadArticle>, String> getAll() {
        Either<List<ReadArticle>, String> response;

        if (daoReadArticle.getAll().isLeft()) {
            response = Either.left(daoReadArticle.getAll().getLeft());
        } else {
            response = Either.right(daoReadArticle.getAll().get());
        }

        return response;
    }

    public boolean addReadArticle(String idString, String readername, String articleName, String ratingString) {
        if (daoReaders.getAll().isLeft() && daoArticles.getAll().isLeft()){
            int id = Integer.parseInt(idString);
            int idReader = Objects.requireNonNull(daoReaders.getAll().getLeft().stream().filter(reader -> reader.getName().equals(readername))
                    .findFirst().orElse(null)).getId();
            int idArticle = Objects.requireNonNull(daoArticles.getAll().getLeft().stream().filter(article -> article.getTitle().equals(articleName))
                    .findFirst().orElse(null)).getId();
            int rating = Integer.parseInt(ratingString);

            return daoReadArticle.addReadArticle(id, idReader, idArticle, rating);
        } else {
            return false;
        }
    }
}
