package servicios;

import domain.model.Newspaper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ServicesNewspaper {
    List<Newspaper>getAll();

    Newspaper addNewspaper(String name, LocalDate releaseDate);

    Newspaper deleteNewspaper(int id);

    boolean checkArticles (int id);
}
