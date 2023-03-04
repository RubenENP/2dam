package model.readArticle;

import lombok.Getter;
@Getter
public class ReadArticle {
    private int id;
    private int idReader;
    private int idArticle;
    private int ranking;

    public ReadArticle(int idReader, int idArticle, int rating) {
        this.idReader = idReader;
        this.idArticle = idArticle;
        this.ranking = rating;
    }

    public ReadArticle(int id, int idReader, int idArticle, int rating) {
        this.id = id;
        this.idReader = idReader;
        this.idArticle = idArticle;
        this.ranking = rating;
    }
}
