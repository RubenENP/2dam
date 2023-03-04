package services.impl;

import dao.*;
import jakarta.inject.Inject;
import model.ReaderHib;
import services.ServicesReaders;
import io.vavr.control.Either;

import java.sql.Date;
import java.util.List;

public class ServicesReadersImpl implements ServicesReaders {
    ReadersHibDao readersHibDao;
    LoginDao loginDao;

    @Inject
    ServicesReadersImpl(ReadersHibDao readersHibDao, LoginDao loginDao) {
        this.readersHibDao = readersHibDao;
        this.loginDao = loginDao;
    }

    public Either<String, List<ReaderHib>> getAll() {
        return readersHibDao.getAll();
    }

    public Either<String, ReaderHib> get(int id) {
        return readersHibDao.get(id);
    }

    public Either<String, List<ReaderHib>> getAllByNewspaper(String newspaperName) {
        return readersHibDao.getAllByNewspaper(newspaperName);
    }

    public Either<String, ReaderHib> delete(ReaderHib r) {
        return readersHibDao.delete(r);
    }

    public Either<String, ReaderHib> addReader(int id, String name, String birthDate) {
        ReaderHib r = new ReaderHib(id, name, Date.valueOf(birthDate));
        return readersHibDao.add(r);
    }

    public Either<String, ReaderHib> update(String newName, int id, String birthDate) {
        ReaderHib r = new ReaderHib(id, newName, Date.valueOf(birthDate));
        return readersHibDao.update(r);
    }

    public Either<String, List<ReaderHib>> getReaderOfAnArticleType(String description) {
        return readersHibDao.getAll(description);
    }
}
