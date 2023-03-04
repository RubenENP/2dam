package com.example.graphqlrubenhita.data.mapper;

import com.example.graphqlrubenhita.data.modelo.NewspaperEntity;
import com.example.graphqlrubenhita.domain.models.Newspaper;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class NewspaperMapper {

    private ArticleMapper articleMapper;

    public NewspaperMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Newspaper toNewspaper(NewspaperEntity n) {
        return new Newspaper(n.getId(),
                n.getNameNewspaper(),
                n.getReleaseDate().toLocalDate(),
                n.getArticles().stream()
                        .map(articleMapper::toArticle)
                        .toList());
    }


    public NewspaperEntity toNewspaperEntity(Newspaper n) {
        NewspaperEntity newspaperEntity = new NewspaperEntity(
                n.getId(),
                n.getNameNewspaper(),
                Date.valueOf(n.getReleaseDate()),
                null
        );

        if (n.getArticles() != null){
            newspaperEntity.setArticles(
                    n.getArticles().stream()
                            .map(article -> articleMapper.toArticleEntity(article, newspaperEntity))
                            .toList()
            );
        }

        return newspaperEntity;
    }
}
