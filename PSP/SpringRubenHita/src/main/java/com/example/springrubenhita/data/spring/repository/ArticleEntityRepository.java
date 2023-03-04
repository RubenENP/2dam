package com.example.springrubenhita.data.spring.repository;

import com.example.springrubenhita.data.modelo.ArticleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleEntityRepository extends ListCrudRepository<ArticleEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ArticleEntity a set a.nombre = ?1,a.descripcion = ?2 where a.id = ?3")
    int updateArticleValues(String nombre, String descripcion, int id);
}
