package model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "article")

@NamedQueries({ @NamedQuery(name = "HQL_GET_ALL_ARTICLE",
        query = "from Article") })
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name_article")
    private String nameArticle;
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "id_type")
    @ManyToOne
    private ArticleTypeHib type;
    @JoinColumn(name = "id_newspaper", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private NewspaperHib idNewspapers;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return nameArticle;
    }
}
