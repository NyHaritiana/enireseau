package com.enireseau.enireseau.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.w3c.dom.Text;

@Entity
@Table(name = "\"ETUDIANT\"")
public class Etudiant {
    @Id
    private int num_matr;
    private String nom_etud;
    private String prenom_etud;
    @Column(unique = true)
    private String email_etud;
    private String niveau_etud;
    private String parcours_etud;
    private String mot_de_passe;
    private Boolean en_ligne;

    public Etudiant() {
    }

    public Etudiant(int num_matr, String nom_etud, String prenom_etud, String email_etud, String niveau_etud, String parcours_etud, String mot_de_passe, Boolean en_ligne) {
        this.num_matr = num_matr;
        this.nom_etud = nom_etud;
        this.prenom_etud = prenom_etud;
        this.email_etud = email_etud;
        this.niveau_etud = niveau_etud;
        this.parcours_etud = parcours_etud;
        this.mot_de_passe = mot_de_passe;
        this.en_ligne = en_ligne;
    }

    public int getNum_matr() {
        return num_matr;
    }

    public void setNum_matr(int num_matr) {
        this.num_matr = num_matr;
    }

    public String getNom_etud() {
        return nom_etud;
    }

    public void setNom_etud(String nom_etud) {
        this.nom_etud = nom_etud;
    }

    public String getPrenom_etud() {
        return prenom_etud;
    }

    public void setPrenom_etud(String prenom_etud) {
        this.prenom_etud = prenom_etud;
    }

    public String getEmail_etud() {
        return email_etud;
    }

    public void setEmail_etud(String email_etud) {
        this.email_etud = email_etud;
    }

    public String getNiveau_etud() {
        return niveau_etud;
    }

    public void setNiveau_etud(String niveau_etud) {
        this.niveau_etud = niveau_etud;
    }

    public String getParcours_etud() {
        return parcours_etud;
    }

    public void setParcours_etud(String parcours_etud) {
        this.parcours_etud = parcours_etud;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Boolean getEn_ligne() {
        return en_ligne;
    }

    public void setEn_ligne(Boolean en_ligne) {
        this.en_ligne = en_ligne;
    }
}