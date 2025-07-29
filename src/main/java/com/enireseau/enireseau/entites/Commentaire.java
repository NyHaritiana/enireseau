package com.enireseau.enireseau.entites;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"COMMENTAIRE\"")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comment;

    private int id_pub;
    private int num_matr;

    private String contenu;

    private LocalDateTime date_comment;

    public Commentaire() {
    }

    public Commentaire(int id_pub, int num_matr, String contenu, LocalDateTime date_comment) {
        this.id_pub = id_pub;
        this.num_matr = num_matr;
        this.contenu = contenu;
        this.date_comment = date_comment;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public int getNum_matr() {
        return num_matr;
    }

    public void setNum_matr(int num_matr) {
        this.num_matr = num_matr;
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
}
