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
        try {
            etudiantRepository.save(etudiant);
            logger.info("Étudiant créé avec succès: {}", etudiant.getNum_matr());
        } catch (Exception e) {
            logger.error("Erreur lors de la création de l'étudiant: {}", e.getMessage(), e);
        }
    }

    public List<Etudiant> liste() {
        try {
            return etudiantRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la liste des étudiants: {}", e.getMessage(), e);
            return List.of(); // Retourne une liste vide en cas d'erreur
        }
    }

    public Etudiant list(String num_matr) {
        try {
            Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(num_matr);
            if (optionalEtudiant.isPresent()) {
                return optionalEtudiant.get();
            } else {
                logger.warn("Aucun étudiant trouvé avec le matricule: {}", num_matr);
                return null;
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la recherche de l'étudiant {}: {}", num_matr, e.getMessage(), e);
            return null;
        }
    }

    public void supprime(String num_matr) {
        try {
            etudiantRepository.deleteById(num_matr);
            logger.info("Étudiant supprimé avec succès: {}", num_matr);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'étudiant {}: {}", num_matr, e.getMessage(), e);
        }
    }

    public void modifie(String num_matr, Etudiant etudiant) {
        try {
            Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(num_matr);
            if (optionalEtudiant.isPresent()) {
                Etudiant etudiant1 = optionalEtudiant.get();
                etudiant1.setNom_etud(etudiant.getNom_etud());
                etudiant1.setPrenom_etud(etudiant.getPrenom_etud());
                etudiant1.setEmail_etud(etudiant.getEmail_etud());
                etudiant1.setParcours_etud(etudiant.getParcours_etud());
                etudiant1.setNiveau_etud(etudiant.getNiveau_etud());
                etudiant1.setMot_de_passe(etudiant.getMot_de_passe());
                etudiantRepository.save(etudiant1);
                logger.info("Étudiant mis à jour: {}", num_matr);
            } else {
                logger.warn("Impossible de modifier: étudiant non trouvé avec le matricule {}", num_matr);
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la modification de l'étudiant {}: {}", num_matr, e.getMessage(), e);
        }
    }
}
