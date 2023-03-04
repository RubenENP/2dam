package services;

import model.ArticleType;

import java.util.List;

public interface ServicesArticleType {
    List<ArticleType> getAllTypes();
    List<String> getAllNameTypes();
}
