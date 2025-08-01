package com.enireseau.enireseau.repository;

import com.enireseau.enireseau.entites.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    boolean existsByEmailEtud(String emailEtud);
}
