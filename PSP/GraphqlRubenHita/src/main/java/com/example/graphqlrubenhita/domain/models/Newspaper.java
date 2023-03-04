package com.example.graphqlrubenhita.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Newspaper{
    private int id;
    private String nameNewspaper;
    private LocalDate releaseDate;
    private List<Article> articles;
}
