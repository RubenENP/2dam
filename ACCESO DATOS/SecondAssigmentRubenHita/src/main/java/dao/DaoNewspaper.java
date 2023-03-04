package dao;

import io.vavr.control.Either;
import model.Newspaper;

import java.io.IOException;
import java.util.List;

public interface DaoNewspaper {
    List<Newspaper> getAll();
    Newspaper addNewspaper(Newspaper n);
    Newspaper delete(Newspaper n);
}
