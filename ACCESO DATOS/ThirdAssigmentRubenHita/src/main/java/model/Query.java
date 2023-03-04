package model;

import lombok.Data;

@Data
public class Query {
    private final String description;
    private final int numReader;
    private final String name_article;
}
