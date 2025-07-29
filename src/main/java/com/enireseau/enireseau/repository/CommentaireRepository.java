package com.enireseau.enireseau.repository;

import com.enireseau.enireseau.entites.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
}
