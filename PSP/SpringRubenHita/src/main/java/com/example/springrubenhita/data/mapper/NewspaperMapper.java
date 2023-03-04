package com.example.springrubenhita.data.mapper;

import com.example.springrubenhita.data.modelo.NewspaperEntity;
import com.example.springrubenhita.domain.models.Newspaper;
import org.springframework.stereotype.Component;

@Component
public class NewspaperMapper {

    private ArticleMapper articleMapper;

    public NewspaperMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public Newspaper toNewspaper(NewspaperEntity n) {
        return new Newspaper(n.getId(),
                n.getNameNewspaper(),
                n.getReleaseDate(),
                n.getArticles().stream()
                        .map(articleMapper::toArticle)
                        .toList());
    }


    public NewspaperEntity toNewspaperEntity(Newspaper n) {
        NewspaperEntity newspaperEntity = new NewspaperEntity(
                n.getId(),
                n.getNameNewspaper(),
                n.getReleaseDate(),
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
