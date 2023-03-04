package com.example.graphqlrubenhita.spring.repository;

import com.example.graphqlrubenhita.data.modelo.NewspaperEntity;
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
    @Query("update NewspaperEntity n set n.nameNewspaper = ?1 where n.id = ?2")
    int updateNewspaperValues(String nameNewspaper, int id);
}