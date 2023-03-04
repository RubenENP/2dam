package services;

import io.vavr.control.Either;
import model.Newspaper;

import java.sql.Date;
import java.util.List;

public interface ServicesNewspaper {
    Either<List<Newspaper>, String>getAll();

    Newspaper addNewspaper(String name, Date releaseDate);

    Newspaper deleteNewspaper(int id);

    boolean checkArticles (int id);
}
