package model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "reader")

@NamedQueries({ @NamedQuery(name = "HQL_GET_ALL_READERS", query = "from ReaderHib "),
        @NamedQuery(name="HQL_GET_ALL_READERS_OF_TYPE", query = "SELECT DISTINCT rA.reader from ReadArticle rA WHERE rA.article.type.description = :descripcion"),
        @NamedQuery(name = "HQL_GET_ALL_READERS_OF_NEWSPAPER", query = "SELECT s.reader from Subscription s WHERE s.newspaper.nameNewspaper = :name")})
public class ReaderHib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_reader")
    private String nameReader;

    @Column(name = "birth_reader")
    private Date birthReader;
}
