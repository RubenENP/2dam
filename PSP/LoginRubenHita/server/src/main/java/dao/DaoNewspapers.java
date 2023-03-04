package dao;

import domain.model.Newspaper;

import java.util.List;

public interface DaoNewspapers {
    List<Newspaper> getAll();
    Newspaper addNewspaper(Newspaper n);
    Newspaper delete(Newspaper n);
}
