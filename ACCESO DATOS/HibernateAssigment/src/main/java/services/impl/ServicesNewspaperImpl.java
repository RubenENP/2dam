package services.impl;

import dao.NewspaperHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.NewspaperHib;
import services.ServicesNewspaper;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        int  deleteNewspaper = newspaperHibDao.delete(newspaperHib);

        if (deleteNewspaper == 1){
            response = Either.left(newspaperHib.getNameNewspaper()+" cannot be deleted because it has articles");
        } else {
            response = Either.right(newspaperHib);
        }

        return response;
    }

    public Map<String, Integer> getNbrArticles(int newspaperId){
        return newspaperHibDao.getNbrArticles(newspaperId);
    }
}
