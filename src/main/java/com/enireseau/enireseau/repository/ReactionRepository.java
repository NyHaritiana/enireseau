package com.enireseau.enireseau.repository;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.entites.Publication;
import com.enireseau.enireseau.entites.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
    Optional<Reaction> findByPublicationAndEtudiant(Publication publication, Etudiant etudiant);
    long countByPublication(Publication publication);
}
