package model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "type")

@NamedQueries({ @NamedQuery(name = "HQL_GET_ALL_ARTICLETYPE",
        query = "from ArticleTypeHib "),
@NamedQuery(name = "HQL_GET_MOREREAD_TYPES",
    query = "select rA.article.type from ReadArticle rA where rA.articleId = (SELECT rA.articleId FROM ReadArticle rA GROUP BY rA.articleId ORDER BY rA.articleId DESC LIMIT 1)")})
public class ArticleTypeHib {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
}
