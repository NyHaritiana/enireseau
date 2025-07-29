package com.enireseau.enireseau.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ETUDIANT\"")
public class Etudiant {
    @Id
    private int num_matr;
    private String nom_etud;
    private String prenom_etud;
    @Column(unique = true)
    private String email_etud;
    private String classe_etud;

    public Etudiant() {
    }

    public Etudiant(int num_matr, String nom_etud, String prenom_etud, String email_etud, String classe_etud) {
        this.num_matr = num_matr;
        this.nom_etud = nom_etud;
        this.prenom_etud = prenom_etud;
        this.email_etud = email_etud;
        this.classe_etud = classe_etud;
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

    public String getClasse_etud() {
        return classe_etud;
    }

    public void setClasse_etud(String classe_etud) {
        this.classe_etud = classe_etud;
    }
}
