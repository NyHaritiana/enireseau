package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.repository.EtudiantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    private static final Logger logger = LoggerFactory.getLogger(EtudiantService.class);
    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public void cree(Etudiant etudiant) {
        if (etudiantRepository.existsByEmailEtud(etudiant.getEmail_etud())) {
            throw new IllegalArgumentException("L'email est déjà utilisé.");
        }
        etudiantRepository.save(etudiant);
        logger.info("Étudiant créé avec succès: {}", etudiant.getNum_matr());
    }

    public List<Etudiant> liste() {
        return etudiantRepository.findAll();
    }

    public Etudiant list(String num_matr) {
        return etudiantRepository.findById(num_matr).orElse(null);
    }

    public void supprime(String num_matr) {
        if (etudiantRepository.existsById(num_matr)) {
            etudiantRepository.deleteById(num_matr);
            logger.info("Étudiant supprimé avec succès: {}", num_matr);
        } else {
            logger.warn("Suppression échouée : étudiant introuvable avec matricule {}", num_matr);
            throw new IllegalArgumentException("Étudiant introuvable");
        }
    }

    public boolean modifie(String num_matr, Etudiant etudiant) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(num_matr);
        if (optionalEtudiant.isPresent()) {
            Etudiant etudiantExistant = optionalEtudiant.get();
            etudiantExistant.setNom_etud(etudiant.getNom_etud());
            etudiantExistant.setPrenom_etud(etudiant.getPrenom_etud());
            etudiantExistant.setEmail_etud(etudiant.getEmail_etud());
            etudiantExistant.setParcours_etud(etudiant.getParcours_etud());
            etudiantExistant.setNiveau_etud(etudiant.getNiveau_etud());
            etudiantExistant.setMot_de_passe(etudiant.getMot_de_passe());
            etudiantExistant.setEn_ligne(etudiant.getEn_ligne());
            etudiantRepository.save(etudiantExistant);
            logger.info("Étudiant mis à jour: {}", num_matr);
            return true;
        } else {
            logger.warn("Échec modification : étudiant introuvable avec matricule {}", num_matr);
            return false;
        }
    }

    public boolean mettreEnLigne(String num_matr, boolean enLigne) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(num_matr);
        if (optionalEtudiant.isPresent()) {
            Etudiant e = optionalEtudiant.get();
            e.setEn_ligne(enLigne);
            etudiantRepository.save(e);
            logger.info("Étudiant {} mis à jour (en_ligne = {})", num_matr, enLigne);
            return true;
        }
        logger.warn("Étudiant introuvable pour changement d'état en_ligne: {}", num_matr);
        return false;
    }
}
