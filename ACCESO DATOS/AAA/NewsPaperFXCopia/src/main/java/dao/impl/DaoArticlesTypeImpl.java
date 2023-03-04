package dao.impl;

import config.ConfigProperties;
import dao.DaoArticlesType;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.ArticleType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoArticlesTypeImpl implements DaoArticlesType {
    public Either<List<ArticleType>, String> getAll(){
        Either<List<ArticleType>, String> response;

        Path file = Paths.get(ConfigProperties.getInstance().getProperty("articleTypeList"));

        List<ArticleType> articleTypeList = new ArrayList<>();

        try {
            List<String> typeString = Files.readAllLines(file);
            typeString.forEach(line -> articleTypeList.add(new ArticleType(line)));
            response = Either.left(articleTypeList);
        } catch (IOException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }
}
