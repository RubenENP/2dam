package services.impl;

import dao.NewspaperHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.NewspaperHib;
import services.ServicesNewspaper;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class ServicesNewspaperImpl implements ServicesNewspaper {

    NewspaperHibDao newspaperHibDao;

    @Inject
    ServicesNewspaperImpl(NewspaperHibDao newspaperHibDao){
        this.newspaperHibDao = newspaperHibDao;
    }

    public Either<String, List<NewspaperHib>> getAll() {
        return newspaperHibDao.getAll();
    }

    public Either<String, NewspaperHib> get(NewspaperHib newspaperHib){
        return newspaperHibDao.get(newspaperHib);
    }

    public Either<String, NewspaperHib> addNewspaper(String name, Date releaseDate){
        return newspaperHibDao.add(new NewspaperHib(0,name, releaseDate, Collections.emptyList()));
    }

    public Either<String, NewspaperHib> deleteNewspaper(NewspaperHib newspaperHib){
        Either<String, NewspaperHib> response;
        Either<String, NewspaperHib> deleteNewspaper = newspaperHibDao.delete(newspaperHib);

        if (deleteNewspaper.isLeft()){
            response = Either.left(newspaperHib.getNameNewspaper()+" cannot be deleted because it has articles");
        } else {
            response = Either.right(deleteNewspaper.get());
        }

        return response;
    }
}
