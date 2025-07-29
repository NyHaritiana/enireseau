package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.services.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "etudiant")
public class EtudiantController {

    private EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void cree(@RequestBody Etudiant etudiant){
        this.etudiantService.cree(etudiant);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Etudiant> liste(){
        return this.etudiantService.liste();
    }

    @DeleteMapping(path = "{num_matr}")
    public void supprime(@PathVariable int num_matr){
        this.etudiantService.supprime(num_matr);
    }

    @PutMapping(path = "{num_matr}", consumes = APPLICATION_JSON_VALUE)
    public void modifie(@PathVariable int num_matr, @RequestBody Etudiant etudiant){
        this.etudiantService.modifie(num_matr, etudiant);
    }
}
