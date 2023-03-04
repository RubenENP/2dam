package services.impl;

import dao.DaoArticles;
import dao.DaoArticlesType;
import dao.DaoNewspaper;
import dao.DaoReaders;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import model.Article;
import model.Newspaper;
import model.reader.Reader;
import services.ServicesReaders;
import io.vavr.control.Either;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicesReadersImpl implements ServicesReaders {
    DaoReaders daoReaders;
    DaoArticles daoArticles;
    DaoArticlesType daoArticlesType;
    DaoNewspaper daoNewspaper;

    @Inject
    ServicesReadersImpl(DaoReaders daoReaders, DaoArticles daoArticles, DaoArticlesType daoArticlesType,
                        DaoNewspaper daoNewspaper) {
        this.daoReaders = daoReaders;
        this.daoArticles = daoArticles;
        this.daoArticlesType = daoArticlesType;
        this.daoNewspaper = daoNewspaper;
    }

    public Either<List<Reader>, String> getAll() {
        Either<List<Reader>, String> response;

        if (daoReaders.getAll().isLeft()){
            response = Either.left(daoReaders.getAll().getLeft());
        }else {
            response = Either.right(daoReaders.getAll().get());
        }

        return response;
    }

    public Either<List<Reader>, String> getReadersByArticleType(String name) {
        Either<List<Reader>, String> response;

        if (daoArticlesType.getAll().isLeft()){
            int articleTypeId = Objects.requireNonNull(daoArticlesType.getAll().getLeft().stream()
                    .filter(articleType -> articleType.getName().equals(name))
                    .findFirst().orElse(null)).getId();

            if(getAll().isLeft()){
                List<Reader> readerList = getAll().getLeft();
                List<Reader> readerListResult = new ArrayList<>();

                List<Article> allArticlesWithType = daoArticles.getAll().getLeft().stream().filter(article -> article.getIdType() == articleTypeId)
                        .toList();

                readerList.forEach(reader -> allArticlesWithType.forEach(article -> {
                    if (reader.getReadArticles().getReadArticle().stream().filter(article1 -> article1.getIdArticle() == article.getId())
                            .findFirst().orElse(null) != null){
                        readerListResult.add(reader);
                    }
                }));

                response = Either.left(readerListResult);
            } else {
                response = Either.right(getAll().get());
            }
        } else {
            response = Either.right(daoArticlesType.getAll().get());
        }

        return response;
    }

    public Either<List<Reader>, String> getReadersByNewspaper(String newspaperName) {
        Either<List<Reader>, String> response;

        if (daoNewspaper.getAll().isLeft()){
            int newspaperId = Objects.requireNonNull(daoNewspaper.getAll().getLeft()
                    .stream().filter(newspaper -> newspaper.getNombre().equals(newspaperName))
                    .findFirst().orElse(null)).getId();

            if (daoReaders.getAll().isLeft()){
                List<Reader> readerList = daoReaders.getAll().getLeft();
                List<Reader> readerListResult = new ArrayList<>();

                readerList.forEach(reader -> {
                    if (reader.getSubscriptions().getSubscription().stream()
                            .filter(subscription -> subscription.getIdNewspaper() == newspaperId)
                            .findFirst().orElse(null) != null){
                        readerListResult.add(reader);
                    }
                });

                response = Either.left(readerListResult);
            } else {
                response = Either.right(daoReaders.getAll().get());
            }
        } else {
            response = Either.right(daoNewspaper.getAll().get());
        }

        return response;
    }

    public Either<List<String>, String> getReadersName(){
        Either<List<String>, String> response;

        if (daoReaders.getAll().isLeft()){
            response = Either.left(daoReaders.getAll().getLeft().stream().map(Reader::getName).toList());
        } else {
            response = Either.right(daoReaders.getAll().get());
        }

        return response;
    }

    public boolean delete(int id) {
        return daoReaders.delete(id);
    }
}
