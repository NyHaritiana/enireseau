package com.enireseau.enireseau.entites;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"COMMENTAIRE\"")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comment;

    private String contenu;

    private LocalDateTime date_comment;

    @ManyToOne
    @JoinColumn(name = "id_pub")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "num_matr")
    private Etudiant etudiant;

    public Commentaire() {
    }

    public Commentaire(int id_comment, String contenu, LocalDateTime date_comment, Publication publication, Etudiant etudiant) {
        this.id_comment = id_comment;
        this.contenu = contenu;
        this.date_comment = date_comment;
        this.publication = publication;
        this.etudiant = etudiant;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDate_comment() {
        return date_comment;
    }

    public void setDate_comment(LocalDateTime date_comment) {
        this.date_comment = date_comment;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
