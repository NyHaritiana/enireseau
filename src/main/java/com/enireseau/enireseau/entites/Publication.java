package com.enireseau.enireseau.entites;

import com.enireseau.enireseau.enums.ReactPub;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"PUBLICATION\"")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pub;

    @ManyToOne
    @JoinColumn(name = "num_matr")
    private Etudiant etudiant;
    private String descri_pub;
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> images;

    @Column(name = "nb_likes")
    private int nbLikes = 0;

    public Publication() {
    }

    public Publication(int id_pub, Etudiant etudiant, String descri_pub, List<Image> images, int nbLikes) {
        this.id_pub = id_pub;
        this.etudiant = etudiant;
        this.descri_pub = descri_pub;
        this.images = images;
        this.nbLikes = nbLikes;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getDescri_pub() {
        return descri_pub;
    }

    public void setDescri_pub(String descri_pub) {
        this.descri_pub = descri_pub;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }
}