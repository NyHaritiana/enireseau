package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    private EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public void cree(Etudiant etudiant){
        this.etudiantRepository.save(etudiant);
    }

    public List<Etudiant> liste(){
        return this.etudiantRepository.findAll();
    }

    public Etudiant list(int num_matr){
        Optional<Etudiant> optionalEtudiant = this.etudiantRepository.findById(num_matr);
        return optionalEtudiant.orElse(null);
    }

    public void supprime(int num_matr) {
        this.etudiantRepository.deleteById(num_matr);
    }

    public void modifie(int num_matr, Etudiant etudiant) {
        Etudiant etudiant1 = this.list(num_matr);
        if (etudiant1.getNum_matr() == etudiant.getNum_matr()){
            etudiant1.setNom_etud(etudiant.getNom_etud());
            etudiant1.setPrenom_etud(etudiant.getPrenom_etud());
            etudiant1.setEmail_etud(etudiant.getEmail_etud());
            etudiant1.setNiveau_etud(etudiant.getNiveau_etud());
            etudiant1.setParcours_etud(etudiant.getParcours_etud());
            etudiant1.setMot_de_passe(etudiant.getMot_de_passe());
            this.etudiantRepository.save(etudiant1);
        }
    }
}
