package com.example.springrubenhita.data.spring.repository;

import com.example.springrubenhita.data.modelo.NewspaperEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
public interface NewspaperEntityRepository extends ListCrudRepository<NewspaperEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update NewspaperEntity n set n.nameNewspaper = ?1, n.releaseDate = ?2 where n.id = ?3")
    int updateNewspaperValues(String nameNewspaper, Date releaseDate, int id);
}