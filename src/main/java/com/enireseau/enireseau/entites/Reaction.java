package com.enireseau.enireseau.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "\"REACTION\"")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_react;

    @ManyToOne
    @JoinColumn(name = "id_pub")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "num_matr")
    private Etudiant etudiant;

    public Reaction() {}

    public Reaction(Publication publication, Etudiant etudiant) {
        this.publication = publication;
        this.etudiant = etudiant;
    }

    public int getId() {
        return id_react;
    }

    public void setId(int id_react) {
        this.id_react = id_react;
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

