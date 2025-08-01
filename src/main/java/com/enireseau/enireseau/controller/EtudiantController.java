package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.services.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "inscription")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cree(@RequestBody Etudiant etudiant) {
        try {
            etudiantService.cree(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création.");
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Etudiant>> liste() {
        return ResponseEntity.ok(etudiantService.liste());
    }

    @GetMapping(path = "{num_matr}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable String num_matr) {
        Etudiant etudiant = etudiantService.list(num_matr);
        if (etudiant != null) {
            return ResponseEntity.ok(etudiant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
        }
    }

    @DeleteMapping(path = "{num_matr}")
    public ResponseEntity<?> supprime(@PathVariable String num_matr) {
        try {
            etudiantService.supprime(num_matr);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur de suppression");
        }
    }

    @PutMapping(path = "{num_matr}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifie(@PathVariable String num_matr, @RequestBody Etudiant etudiant) {
        boolean success = etudiantService.modifie(num_matr, etudiant);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
        }
    }

    @PatchMapping("{num_matr}/connexion")
    public ResponseEntity<?> connecter(@PathVariable String num_matr) {
        if (etudiantService.mettreEnLigne(num_matr, true)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
    }

    @PatchMapping("{num_matr}/deconnexion")
    public ResponseEntity<?> deconnecter(@PathVariable String num_matr) {
        if (etudiantService.mettreEnLigne(num_matr, false)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
    }
}
