package com.example.graphqlrubenhita.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article{
    private int id;
    private String nombre;
    private String descripcion;
}
