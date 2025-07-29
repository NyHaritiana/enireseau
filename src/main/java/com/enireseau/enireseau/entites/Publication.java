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

    @Enumerated(EnumType.STRING)
    private ReactPub react_pub;
    @ManyToOne
    @JoinColumn(name = "num_matr")
    private Etudiant etudiant;
    private String descri_pub;
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Image> images;



    public Publication() {
    }

    public Publication(int id_pub, ReactPub react_pub, Etudiant etudiant, String descri_pub, List<Image> images) {
        this.id_pub = id_pub;
        this.react_pub = react_pub;
        this.etudiant = etudiant;
        this.descri_pub = descri_pub;
        this.images = images;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public ReactPub getReact_pub() {
        return react_pub;
    }

    public void setReact_pub(ReactPub react_pub) {
        this.react_pub = react_pub;
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
}
