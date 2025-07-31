package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Publication;
import com.enireseau.enireseau.services.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "publication")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Publication publication) {
        this.publicationService.creer(publication);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void creerAvecFichiers(
            @RequestParam("descri_pub") String descri_pub,
            @RequestParam("num_matr") String num_matr,
            @RequestParam("files") List<MultipartFile> files
    ) throws IOException {
        publicationService.creerAvecImages(descri_pub, num_matr, files);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Publication> liste() {
        return this.publicationService.liste();
    }

    @DeleteMapping(path = "{id_pub}")
    public void supprimer(@PathVariable int id_pub) {
        this.publicationService.supprimer(id_pub);
    }

    @PutMapping(path = "{id_pub}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void modifier(@PathVariable int id_pub, @RequestBody Publication publication) {
        this.publicationService.modifier(id_pub, publication);
    }

    @PutMapping("/{id_pub}/like/{num_matr}")
    public void toggleReaction(@PathVariable int id_pub, @PathVariable String num_matr) {
        publicationService.toggleReaction(id_pub, num_matr);
    }

    @GetMapping("/{id_pub}/likes")
    public int getNombreLikes(@PathVariable int id_pub) {
        Publication pub = publicationService.list(id_pub);
        return pub != null ? pub.getNbLikes() : 0;
    }
}
