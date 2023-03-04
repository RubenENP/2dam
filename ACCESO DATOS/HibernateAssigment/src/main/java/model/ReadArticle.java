package model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "readarticle")

@NamedQueries({@NamedQuery(name = "HQL_GET_ALL_READERARTICLE", query = "from ReadArticle "),})
public class ReadArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Article article;

    @Column(name = "id_article")
    private int articleId;

    @ManyToOne
    @JoinColumn(name = "id_reader", referencedColumnName = "id",nullable = false, insertable = false, updatable = false)
    private ReaderHib reader;
    @Column(name = "ranking")
    private int ranking;
}
