package com.enireseau.enireseau.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "\"IMAGE\"")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_image;
    @ManyToOne
    @JoinColumn(name = "id_pub")
    @JsonBackReference
    private Publication publication;


    private String chemin;

    public Image() {
    }

    public Image(int id_image, Publication publication, String chemin) {
        this.id_image = id_image;
        this.publication = publication;
        this.chemin = chemin;
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}