package services.impl;

import dao.*;
import jakarta.inject.Inject;
import model.reader.Reader;
import services.ServicesReaders;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public class ServicesReadersImpl implements ServicesReaders {
    DaoReaders daoReaders;
    DaoArticles daoArticles;
    DaoArticlesType daoArticlesType;
    DaoNewspaper daoNewspaper;
    DaoSubscriptions daoSubscriptions;

    @Inject
    ServicesReadersImpl(DaoReaders daoReaders, DaoArticles daoArticles, DaoArticlesType daoArticlesType,
                        DaoNewspaper daoNewspaper, DaoSubscriptions daoSubscriptions) {
        this.daoReaders = daoReaders;
        this.daoArticles = daoArticles;
        this.daoArticlesType = daoArticlesType;
        this.daoNewspaper = daoNewspaper;
        this.daoSubscriptions = daoSubscriptions;
    }

    public Either<List<Reader>, String> getAll() {
        return daoReaders.getAll();
    }

    public int delete(int id) {
        Reader r = new Reader(id);
        return daoReaders.delete(r);
    }

    public int addReader(int id, String name, String birthDate){
        LocalDate localDate = new LocalDateAdapter().unmarshal(birthDate);
        Reader r = new Reader(id, name, localDate);
        return daoReaders.save(r);
    }

    public int update(String newName, int id, String birthDate){
        Reader r = new Reader(id, newName, LocalDate.now());
        return daoReaders.update(r);
    }

    public Either<List<Reader>, String> getAllByNewspaperId(int newspaperId){
        return daoReaders.getAllByNewspaperId(newspaperId);
    }

    public Either<List<Reader>, String> getAllByArticleTypeId(int articleTypeId){
        return daoReaders.getAllByArticleTypeId(articleTypeId);
    }
}
