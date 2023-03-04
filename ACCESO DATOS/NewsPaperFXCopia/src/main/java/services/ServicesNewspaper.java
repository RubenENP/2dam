package services;

import model.Newspaper;

import java.io.IOException;
import java.util.List;

public interface ServicesNewspaper {
    List<Newspaper> getAll();

    boolean addNewspaper(String id, String nombre, String precio, String director);

    boolean deleteNewspaper(int id);

    boolean checkArticles (int id);
}
