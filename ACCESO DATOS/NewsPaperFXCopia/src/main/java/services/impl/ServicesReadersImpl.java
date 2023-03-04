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

    public List<Reader> getAll() throws JAXBException, IOException {
        return daoReaders.getAll();
    }

    public List<Reader> getReadersByArticleType(String name) throws JAXBException, IOException {
        int articleTypeId = Objects.requireNonNull(daoArticlesType.getAll().stream()
                .filter(articleType -> articleType.getName().equals(name))
                .findFirst().orElse(null)).getId();

        List<Reader> readerList = getAll();
        List<Reader> readerListResult = new ArrayList<>();

        List<Article> allArticlesWithType = daoArticles.getAll().stream().filter(article -> article.getIdType() == articleTypeId).toList();

        readerList.forEach(reader -> allArticlesWithType.forEach(article -> {
            if (article.getId() == reader.getReadArticles().getReadArticle().get(0).getIdArticle()){
                readerListResult.add(reader);
            }
        }));


        return readerListResult;
    }

    public List<Reader> getReadersByNewspaper(String newspaperName) throws IOException, JAXBException {
        int newspaperId = Objects.requireNonNull(daoNewspaper.getAll()
                .stream().filter(newspaper -> newspaper.getNombre().equals(newspaperName))
                .findFirst().orElse(null)).getId();

        List<Reader> readerList = daoReaders.getAll();
        List<Reader> readerListResult = new ArrayList<>();

        readerList.forEach(reader -> {
            if (reader.getSubscriptions().getSubscription().stream()
                    .filter(subscription -> subscription.getIdNewspaper() == newspaperId)
                    .findFirst().orElse(null) != null){
                readerListResult.add(reader);
            }
        });

        return readerListResult;
    }
}
