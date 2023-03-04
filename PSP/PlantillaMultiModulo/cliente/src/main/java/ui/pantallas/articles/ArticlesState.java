package ui.pantallas.articles;

import domain.model.Article;
import domain.model.ArticleType;
import domain.model.Newspaper;

import java.util.List;

public record ArticlesState(List<Article> articleList, List<ArticleType> articleTypeList, List<Newspaper> newspaperList, String error) {
}
