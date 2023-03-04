package dao;

import model.Newspaper;

import java.io.IOException;
import java.util.List;

public interface DaoNewspaper {
    List<Newspaper> getAll() throws IOException;
    boolean addNewspaper(String id, String nombre, String precio, String director);

    boolean delete(int id);
}
