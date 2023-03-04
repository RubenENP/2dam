package com.example.graphqlrubenhita.spring.rest;

import com.example.graphqlrubenhita.domain.models.Newspaper;
import com.example.graphqlrubenhita.domain.usecases.NewspaperService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class NewspaperController {
    private final NewspaperService newspaperService;

    public NewspaperController(NewspaperService newspaperService) {
        this.newspaperService = newspaperService;
    }

    @QueryMapping
    public List<Newspaper> getAllNewspapers(){return newspaperService.getAllNewspapers();}

    @QueryMapping
    public Newspaper getNewspaperById(Integer id) {
        return newspaperService.getNewspaperById(id);
    }

    @MutationMapping
    public Newspaper createNewspaper(@Argument String nameNewspaper, @Argument LocalDate releaseDate) {
        Newspaper n = new Newspaper();
        n.setNameNewspaper(nameNewspaper);
        n.setReleaseDate(releaseDate);
        return newspaperService.createNewspaper(n);
    }

    @MutationMapping
    public int updateNewspaper(@Argument Integer id, @Argument String nameNewspaper) {
        return newspaperService.updateNewspaper(id, nameNewspaper);
    }

    @MutationMapping
    public void deleteNewspaper(@PathVariable Integer id) {
        newspaperService.deleteNewspaper(id);
    }
}
