package com.example.springrubenhita.data.spring.rest;

import com.example.springrubenhita.domain.usecases.NewspaperService;
import com.example.springrubenhita.domain.models.Newspaper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newspapers")
public class NewspaperController {
    private final NewspaperService newspaperService;

    public NewspaperController(NewspaperService newspaperService) {
        this.newspaperService = newspaperService;
    }

    @GetMapping
    public List<Newspaper> getAll(){return newspaperService.getAllNewspapers();}

    @GetMapping("/{id}")
    public Newspaper getNewspaperById(@PathVariable int id) {
        return newspaperService.getNewspaperById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Newspaper createNewspaper(@RequestBody Newspaper newspaper) {
        return newspaperService.createNewspaper(newspaper);
    }

    @PutMapping("/{id}")
    public Newspaper updateNewspaper(@PathVariable int id, @RequestBody Newspaper newspaper) {
        return newspaperService.updateNewspaper(id, newspaper);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewspaper(@PathVariable int id) {
        newspaperService.deleteNewspaper(id);
    }
}
