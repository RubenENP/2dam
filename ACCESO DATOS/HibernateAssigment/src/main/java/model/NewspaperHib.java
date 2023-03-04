package model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "newspaper")

@NamedQueries({@NamedQuery(name = "HQL_GET_ALL_NEWSPAPERS", query = "from NewspaperHib "),
})
public class NewspaperHib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_newspaper")
    private String nameNewspaper;

    @Column(name = "release_date")
    private Date releaseDate;
    @OneToMany(mappedBy = "idNewspapers", cascade = {CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER)
    private List<Article> articleList;

}
