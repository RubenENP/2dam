package com.example.springrubenhita.data.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "newspaper")
public class NewspaperEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name_newspaper", nullable = false)
    private String nameNewspaper;

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @OneToMany(mappedBy = "id", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ArticleEntity> articles;
}
