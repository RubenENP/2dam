package servicios.impl;

import dao.*;
import domain.model.reader.Reader;
import jakarta.inject.Inject;
import servicios.ServicesReaders;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public class ServicesReadersImpl implements ServicesReaders {
    DaoReaders daoReaders;
    DaoArticles daoArticles;
    DaoArticlesType daoArticlesType;
    DaoNewspapers daoNewspaper;
    DaoSubscriptions daoSubscriptions;

    @Inject
    ServicesReadersImpl(DaoReaders daoReaders, DaoArticles daoArticles, DaoArticlesType daoArticlesType,
                        DaoNewspapers daoNewspaper, DaoSubscriptions daoSubscriptions) {
        this.daoReaders = daoReaders;
        this.daoArticles = daoArticles;
        this.daoArticlesType = daoArticlesType;
        this.daoNewspaper = daoNewspaper;
        this.daoSubscriptions = daoSubscriptions;
    }

    public List<Reader>getAll() {
        return daoReaders.getAll().getLeft();
    }

    public Either<String, Reader> get(int id, String username){
        return daoReaders.get(id, username);
    }

    public int delete(int id) {
        Reader r = new Reader(id);
        return daoReaders.delete(r);
    }

    public Either<String, Reader> addReader(int id, String name, LocalDate birthDate, String username){
        Reader r = new Reader(id, name, birthDate);
        return daoReaders.save(r, username);
    }

    public int update(String newName, int id, LocalDate birthDate){
        Reader r = new Reader(id, newName, birthDate);
        return daoReaders.update(r);
    }
}
