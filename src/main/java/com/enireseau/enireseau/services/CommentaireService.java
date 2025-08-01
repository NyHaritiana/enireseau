package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Commentaire;
import com.enireseau.enireseau.repository.CommentaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommentaireService.class);

    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public Commentaire ajouterCommentaire(Commentaire commentaire) {
        try {
            commentaire.setDate_comment(LocalDateTime.now());
            return commentaireRepository.save(commentaire);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout du commentaire : {}", e.getMessage());
            return null;
        }
    }

    public List<Commentaire> liste() {
        try {
            return commentaireRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la liste des commentaires: {}", e.getMessage(), e);
            return List.of();
        }
    }
}