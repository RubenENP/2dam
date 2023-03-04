package dao.impl;

import config.ConfigProperties;
import dao.DaoArticles;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoArticlesImpl implements DaoArticles {

    public Either<List<Article>, String> getAll() {
        Either<List<Article>, String> response;

        Path path = Paths.get(ConfigProperties.getInstance().getProperty("articlesList"));
        List<Article> articleList = new ArrayList<>();

        try{
            List<String> list = Files.readAllLines(path);
            list.forEach(article -> articleList.add(new Article(article)));

            response = Either.left(articleList);
        }catch (IOException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public boolean save(String line){
        Path path = Paths.get(ConfigProperties.getInstance().getProperty("articlesList"));
        List<Article> articleList = new ArrayList<>();

        try {
            List<String> list = Files.readAllLines(path);

            articleList.add(new Article(line));
            list.addAll(articleList.stream().map(Article::toString).toList());

            Files.write(path, list);
        } catch (IOException e){
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id){
        Path path = Paths.get(ConfigProperties.getInstance().getProperty("articlesList"));

        if (getAll().isLeft()){
            List<Article> articleList = getAll().getLeft();

            try{

                Article a = articleList.stream().filter(article -> article.getId() == id)
                        .findFirst().orElse(null);

                articleList.remove(a);

                List<String> list = new ArrayList<>(articleList.stream().map(Article::toString).toList());

                Files.write(path, list);
            }catch (IOException e){
                log.error(e.getMessage());
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
