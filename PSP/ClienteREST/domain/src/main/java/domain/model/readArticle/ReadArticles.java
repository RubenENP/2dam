package domain.model.readArticle;

import lombok.Data;

import java.util.List;


@Data
public class ReadArticles {
    private List<ReadArticle> readArticle;

    public void addReadArticle(ReadArticle readArticle) {
        this.readArticle.add(readArticle);
    }
}
