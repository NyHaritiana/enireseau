package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Publication;
import com.enireseau.enireseau.services.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "publication")
public class PublicationController {

    private PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Publication publication){
        this.publicationService.creer(publication);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Publication> liste(){
        return this.publicationService.liste();
    }

    @DeleteMapping(path = "{id_pub}")
    public void supprimer(@PathVariable int id_pub){
        this.publicationService.supprimer(id_pub);
    }

    @PutMapping(path = "{id_pub}", consumes = APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable int id_pub, @RequestBody Publication publication){
        this.publicationService.modifier(id_pub, publication);
    }
}
