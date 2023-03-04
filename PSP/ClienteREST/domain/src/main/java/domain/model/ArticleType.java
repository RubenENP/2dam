package domain.model;

import lombok.Getter;

@Getter
public class ArticleType {
    private final int id;
    private final String name;

    public ArticleType(int id, String name){
        this.id = id;
        this.name = name;
    }
}
