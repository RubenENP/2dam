package services;

import io.vavr.control.Either;
import model.Newspaper;

import java.io.IOException;
import java.util.List;

public interface ServicesNewspaper {
    Either<List<Newspaper>, String> getAll();

    boolean addNewspaper(String id, String nombre, String precio, String director);

    boolean deleteNewspaper(int id);

    boolean checkArticles (int id);
}
