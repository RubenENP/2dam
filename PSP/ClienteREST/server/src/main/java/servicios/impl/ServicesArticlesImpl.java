package servicios.impl;

import dao.DaoArticles;
import dao.DaoArticlesType;
import dao.DaoNewspapers;
import domain.model.Article;
import domain.model.ArticleType;
import domain.model.Newspaper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.ServicesArticles;

import java.util.List;
import java.util.Objects;

public class ServicesArticlesImpl implements ServicesArticles {
    DaoArticles daoArticles;
    DaoArticlesType daoArticlesType;
    DaoNewspapers daoNewspaper;

    @Inject
    ServicesArticlesImpl(DaoArticles daoArticles, DaoArticlesType daoArticlesType, DaoNewspapers daoNewspaper) {
        this.daoArticles = daoArticles;
        this.daoArticlesType = daoArticlesType;
        this.daoNewspaper = daoNewspaper;
    }

    public List<Article> getAllArticles(){
        return daoArticles.getAll();
    }

    public Either<List<Article>, String> getArticlesByType(String descriptionType) {
        Either<List<Article>, String> response;
        int idType;

        if(daoArticlesType.getAll().isLeft()){
            idType = Objects.requireNonNull(daoArticlesType.getAll().getLeft().stream()
                    .filter(articleType -> articleType.getName().equals(descriptionType))
                    .findFirst().orElse(null)).getId();

            List<Article> articleList = daoArticles.getAll(idType);

            if (articleList.isEmpty()) {
                response = Either.right("Empty List");
            } else {
                response = Either.left(articleList);
            }
        } else {
            response = Either.right("No article types");
        }

        return response;
    }

    public Either<List<Article>, String> getArticlesByRating(){
        Either<List<Article>, String> response;

        List<Article> articleList = daoArticles.getAllWithRating();

        if (articleList.isEmpty()){
            response = Either.right("Empty List");
        } else {
            response = Either.left(articleList);
        }

        return response;
    }

    public Either<Article, String> addArticle(String name, String description, String newspaperName, String articletypeName) {
        Either<List<ArticleType>, String> articleTypeList = daoArticlesType.getAll();
        List<Newspaper> newspaperList = daoNewspaper.getAll();

        Either<Article, String> response;

        if (articleTypeList.isLeft()){
            ArticleType articleType = articleTypeList.getLeft().stream().filter(articleType1 -> articleType1.getName().equals(articletypeName))
                    .findFirst().orElse(null);

            Newspaper newspaper = newspaperList.stream().filter(newspaper1 -> newspaper1.getName_newspaper().equals(newspaperName))
                    .findFirst().orElse(null);

            if (articleType!=null&&newspaper!=null){
                Article a = new Article(name, description, newspaper.getId(), articleType.getId());
                response = Either.left(daoArticles.save(a));
            } else {
                response = Either.right("The article type or the newspaper is wrong");
            }

        } else {
            response = Either.right("There is no article type");
        }

        return response;
    }

    public Either<Article, String> addArticle(String name, String description, int newspaperId, int articletypeId) {
        Either<List<ArticleType>, String> articleTypeList = daoArticlesType.getAll();
        List<Newspaper> newspaperList = daoNewspaper.getAll();

        Either<Article, String> response;

        if (articleTypeList.isLeft()){
            ArticleType articleType = articleTypeList.getLeft().stream().filter(articleType1 -> articleType1.getId() == articletypeId)
                    .findFirst().orElse(null);

            Newspaper newspaper = newspaperList.stream().filter(newspaper1 -> newspaper1.getId() == newspaperId)
                    .findFirst().orElse(null);

            if (articleType!=null&&newspaper!=null){
                Article a = new Article(name, description, newspaper.getId(), articleType.getId());
                response = Either.left(daoArticles.save(a));
            } else {
                response = Either.right("The article type or the newspaper is wrong");
            }

        } else {
            response = Either.right("There is no article type");
        }

        return response;
    }

    public Either<List<String>, String> getAllArticlesName() {
        Either<List<String>, String> response;
        List<String> articlesNames = daoArticles.getAll().stream().map(Article::getName_article).toList();

        if (articlesNames.isEmpty()){
            response = Either.right("Empty list");
        } else {
            response = Either.left(articlesNames);
        }

        return response;
    }
}
