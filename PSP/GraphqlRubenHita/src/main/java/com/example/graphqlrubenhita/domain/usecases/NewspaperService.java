package com.example.graphqlrubenhita.domain.usecases;

import com.example.graphqlrubenhita.data.mapper.NewspaperMapper;
import com.example.graphqlrubenhita.data.modelo.NewspaperEntity;
import com.example.graphqlrubenhita.domain.exceptions.NotFoundException;
import com.example.graphqlrubenhita.domain.models.Newspaper;
import com.example.graphqlrubenhita.spring.repository.NewspaperEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewspaperService {
    private final NewspaperEntityRepository newspaperEntityRepository;

    private final NewspaperMapper newspaperMapper;


    public NewspaperService(NewspaperEntityRepository newspaperEntityRepository, NewspaperMapper newspaperMapper) {
        this.newspaperEntityRepository = newspaperEntityRepository;
        this.newspaperMapper = newspaperMapper;
    }

    public List<Newspaper> getAllNewspapers(){
        return newspaperEntityRepository.findAll()
                .stream()
                .map(newspaperMapper::toNewspaper)
                .toList();
    }

    public Newspaper getNewspaperById(int id) {
        return newspaperEntityRepository.findById(id)
                .map(newspaperMapper::toNewspaper)
                .orElseThrow(() -> new NotFoundException("Newspaper not found"));

    }


    public Newspaper createNewspaper(Newspaper newspaper) {
        NewspaperEntity newspaperEntity = newspaperMapper.toNewspaperEntity(newspaper);
        newspaperEntity = newspaperEntityRepository.save(newspaperEntity);
        return newspaperMapper.toNewspaper(newspaperEntity);
    }

    public int updateNewspaper(int id, String nameNewspaper) {
        int numeroFilasUpdate = newspaperEntityRepository.updateNewspaperValues(nameNewspaper, id);
        if (numeroFilasUpdate == 0) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return numeroFilasUpdate;
    }

    public void deleteNewspaper(int id) {
        try {
            newspaperEntityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Newspaper not found");
        }
    }
}
