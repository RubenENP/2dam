package model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "article")

@NamedQueries({@NamedQuery(name = "HQL_GET_ALL_ARTICLE",
        query = "from Article"),
        @NamedQuery(name = "HQL_DELETE", query = "DELETE from Article a WHERE a.newspaperId = :id"),
        @NamedQuery(name = "HQL_SELECT_ARTICLES_FROM_NEWSPAPER",
        query = "SELECT n.articleList from NewspaperHib n WHERE n.nameNewspaper = :newspaperName")})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name_article")
    private String nameArticle;
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "id_type", referencedColumnName = "id")
    @ManyToOne
    private ArticleTypeHib type;

    @Column(name = "id_newspaper", insertable = false, updatable = false)
    private int newspaperId;

    @JoinColumn(name = "id_newspaper", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REMOVE)
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
