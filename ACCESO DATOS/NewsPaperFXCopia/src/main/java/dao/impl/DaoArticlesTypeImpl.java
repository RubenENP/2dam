package dao.impl;

import config.ConfigProperties;
import dao.DaoArticlesType;
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
    public List<ArticleType> getAll(){
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("articleTypeList"));

        List<ArticleType> articleTypeList = new ArrayList<>();

        try {
            List<String> typeString = Files.readAllLines(file);
            typeString.forEach(line -> articleTypeList.add(new ArticleType(line)));
        } catch (IOException e){
            log.error(e.getMessage());
        }

        return articleTypeList;
    }
}
