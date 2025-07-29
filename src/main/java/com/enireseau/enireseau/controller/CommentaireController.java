package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Commentaire;
import com.enireseau.enireseau.services.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "commentaire")
public class CommentaireController {

    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void cree(@RequestBody Commentaire commentaire){
        this.commentaireService.cree(commentaire);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Commentaire> liste(){
        return this.commentaireService.liste();
    }

    @DeleteMapping(path = "{id_comment}")
    public void supprime(@PathVariable int id_comment){
        this.commentaireService.supprime(id_comment);
    }

    @PutMapping(path = "{id_comment}", consumes = APPLICATION_JSON_VALUE)
    public void modifie(@PathVariable int id_comment, @RequestBody Commentaire commentaire){
        this.commentaireService.modifie(id_comment, commentaire);
    }
}
