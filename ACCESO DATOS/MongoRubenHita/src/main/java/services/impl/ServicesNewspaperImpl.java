package services.impl;

import dao.NewspaperDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import services.ServicesNewspaper;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ServicesNewspaperImpl implements ServicesNewspaper {

    NewspaperDao newspaperDao;

    @Inject
    ServicesNewspaperImpl(NewspaperDao newspaperDao){
        this.newspaperDao = newspaperDao;
    }

    public Either<String, List<Newspaper>> getAll() {
        return newspaperDao.getAll();
    }

    public Either<String, Newspaper>delete(Newspaper newspaper){
        int i = newspaperDao.delete(newspaper);

        if (i != 0){
            if (i == 409){
                return Either.left("Cannot delete because the newspaper has articles");
            }else {
                return Either.left("Cannot delete the newspaper");
            }
        } else {
            return Either.right(newspaper);
        }
    }
}
