package dao;

import model.Article;

import java.util.List;

public interface DaoArticles {
    List<Article> getAll();

    boolean save(String line);

    boolean delete(int id);
}
