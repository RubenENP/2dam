package com.example.springrubenhita.domain.usecases;

import com.example.springrubenhita.data.mapper.NewspaperMapper;
import com.example.springrubenhita.data.modelo.NewspaperEntity;
import com.example.springrubenhita.data.spring.repository.NewspaperEntityRepository;
import com.example.springrubenhita.domain.exceptions.NotFoundException;
import com.example.springrubenhita.domain.models.Newspaper;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class NewspaperService {
    private final NewspaperEntityRepository newspaperEntityRepository;

    private final NewspaperMapper newspaperMapper;


    public NewspaperService(NewspaperEntityRepository newspaperEntityRepository, NewspaperMapper newspaperMapper) {
        this.newspaperEntityRepository = newspaperEntityRepository;
        this.newspaperMapper = newspaperMapper;
    }

    @Transactional
    public List<Newspaper> getAllNewspapers(){
        return newspaperEntityRepository.findAll()
                .stream()
                .map(newspaperMapper::toNewspaper)
                .toList();
    }

    @Transactional
    public Newspaper getNewspaperById(int id) {
        return newspaperEntityRepository.findById(id)
                .map(newspaperMapper::toNewspaper)
                .orElseThrow(() -> new NotFoundException("Newspaper not found"));

    }

    @Transactional
    public Newspaper createNewspaper(Newspaper newspaper) {
        NewspaperEntity newspaperEntity = newspaperMapper.toNewspaperEntity(newspaper);
        newspaperEntity = newspaperEntityRepository.save(newspaperEntity);
        log.info(newspaperEntity.getId());
        Newspaper nuevo = newspaperMapper
                .toNewspaper(newspaperEntity);
        log.info("New newspaper: " + nuevo.getId());
        return nuevo;
    }

    public Newspaper updateNewspaper(int id, Newspaper newspaper) {
        int numeroFilasUpdate = newspaperEntityRepository.updateNewspaperValues(newspaper.getNameNewspaper()
                , newspaper.getReleaseDate(), id);
        if (numeroFilasUpdate == 0) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return newspaper;
    }

    public void deleteNewspaper(int id) {
        try {
            newspaperEntityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Newspaper not found");
        }
    }
}
