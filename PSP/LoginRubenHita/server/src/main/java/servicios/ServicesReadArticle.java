package servicios;

import domain.model.readArticle.ReadArticle;

import java.util.List;

public interface ServicesReadArticle {
    List<ReadArticle> getAll();

    int addReadArticle(int idReader, String articleName, int rating);
}
