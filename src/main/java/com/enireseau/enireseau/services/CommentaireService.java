package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Commentaire;
import com.enireseau.enireseau.repository.CommentaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;

    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public void cree(Commentaire commentaire){
        this.commentaireRepository.save(commentaire);
    }

    public List<Commentaire> liste(){
        return this.commentaireRepository.findAll();
    }

    public Commentaire list(int id_comment){
        Optional<Commentaire> optionalCommentaire = this.commentaireRepository.findById(id_comment);
        return optionalCommentaire.orElse(null);
    }

    public void supprime(int id_comment) {
        this.commentaireRepository.deleteById(id_comment);
    }

    public void modifie(int id_comment, Commentaire commentaire) {
        Commentaire commentaire1 = this.list(id_comment);
        if (commentaire1 != null && commentaire1.getId_comment() == commentaire.getId_comment()) {
            commentaire1.setId_pub(commentaire.getId_pub());
            commentaire1.setNum_matr(commentaire.getNum_matr());
            commentaire1.setContenu(commentaire.getContenu());
            commentaire1.setDate_comment(commentaire.getDate_comment());
            this.commentaireRepository.save(commentaire1);
        }
    }
}
