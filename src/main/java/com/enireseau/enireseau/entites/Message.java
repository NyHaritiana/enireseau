package com.enireseau.enireseau.entites;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"MESSAGE\"")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_message;

    private int expediteur;
    private int destinataire;

    private String contenu;
    private LocalDateTime date_envoi;

    public Message() {}

    public Message(int expediteur, int destinataire, String contenu, LocalDateTime date_envoi) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.contenu = contenu;
        this.date_envoi = date_envoi;
    }

    // Getters & Setters
    public int getId_message() { return id_message; }
    public void setId_message(int id_message) { this.id_message = id_message; }

    public int getExpediteur() { return expediteur; }
    public void setExpediteur(int expediteur) { this.expediteur = expediteur; }

    public int getDestinataire() { return destinataire; }
    public void setDestinataire(int destinataire) { this.destinataire = destinataire; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDateTime getDate_envoi() { return date_envoi; }
    public void setDate_envoi(LocalDateTime date_envoi) { this.date_envoi = date_envoi; }
}
