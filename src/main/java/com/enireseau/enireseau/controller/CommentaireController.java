package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Commentaire;
import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.services.CommentaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;
    private static final Logger logger = LoggerFactory.getLogger(CommentaireController.class);

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ajouterCommentaire(@RequestBody Commentaire commentaire) {
        try {
            Commentaire saved = commentaireService.ajouterCommentaire(commentaire);
            if (saved == null) {
                return ResponseEntity.status(500).body("Erreur lors de l'ajout du commentaire.");
            }
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            logger.error("Exception dans le controller : {}", e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne du serveur.");
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Commentaire> liste(){
        return this.commentaireService.liste();
    }
}
