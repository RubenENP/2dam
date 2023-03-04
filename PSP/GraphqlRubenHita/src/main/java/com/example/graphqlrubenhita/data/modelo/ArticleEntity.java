package com.example.graphqlrubenhita.data.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class ArticleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name_article", nullable = false)
    private String nombre;

    @Column(name = "description")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_newspaper")
    private NewspaperEntity newspaper;

}
