package dao;

import io.vavr.control.Either;
import model.Article;
import model.Newspaper;
import model.ReadArticle;

public interface ReadArticleDao {
    Either<String, ReadArticle> add(ReadArticle readArticles, Newspaper newspaper, Article article);
}
