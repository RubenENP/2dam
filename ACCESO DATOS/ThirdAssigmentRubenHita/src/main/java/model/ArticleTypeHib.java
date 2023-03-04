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
        query = "from ArticleTypeHib ") })
public class ArticleTypeHib {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
}
