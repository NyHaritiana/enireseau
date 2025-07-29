package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.entites.Image;
import com.enireseau.enireseau.entites.Publication;
import com.enireseau.enireseau.entites.Reaction;
import com.enireseau.enireseau.repository.EtudiantRepository;
import com.enireseau.enireseau.repository.PublicationRepository;
import com.enireseau.enireseau.repository.ReactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final EtudiantRepository etudiantRepository;
    private final ReactionRepository reactionRepository;

    public PublicationService(PublicationRepository publicationRepository, EtudiantRepository etudiantRepository, ReactionRepository reactionRepository) {
        this.publicationRepository = publicationRepository;
        this.etudiantRepository = etudiantRepository;
        this.reactionRepository = reactionRepository;
    }

    public void creer(Publication publication) {
        if (publication.getEtudiant() != null && publication.getEtudiant().getNum_matr() != 0) {
            int numMatr = publication.getEtudiant().getNum_matr();
            etudiantRepository.findById(numMatr).ifPresent(publication::setEtudiant);
        }

        if (publication.getImages() != null) {
            for (Image image : publication.getImages()) {
                image.setPublication(publication);
            }
        }

        publicationRepository.save(publication);
    }

    public void creerAvecImages(String descri_pub, int num_matr, List<MultipartFile> files) throws IOException {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(num_matr);
        if (etudiantOpt.isEmpty()) return;

        Etudiant etudiant = etudiantOpt.get();

        Publication publication = new Publication();
        publication.setDescri_pub(descri_pub);
        publication.setEtudiant(etudiant);

        List<Image> images = new ArrayList<>();
        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir);

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Image image = new Image();
            image.setChemin(filePath.toString());
            image.setPublication(publication);
            images.add(image);
        }

        publication.setImages(images);
        publicationRepository.save(publication);
    }

    public List<Publication> liste() {
        return this.publicationRepository.findAll();
    }

    public void supprimer(int id_pub) {
        this.publicationRepository.deleteById(id_pub);
    }

    public Publication list(int id_pub) {
        return this.publicationRepository.findById(id_pub).orElse(null);
    }

    public void modifier(int id_pub, Publication publication) {
        Publication publication1 = this.list(id_pub);
        if (publication1 != null) {
            publication1.setDescri_pub(publication.getDescri_pub());
            publication1.setEtudiant(publication.getEtudiant());
            this.publicationRepository.save(publication1);
        }
    }

    public void toggleReaction(int id_pub, int num_matr) {
        Optional<Publication> optPub = publicationRepository.findById(id_pub);
        Optional<Etudiant> optEtud = etudiantRepository.findById(num_matr);

        if (optPub.isEmpty() || optEtud.isEmpty()) return;

        Publication pub = optPub.get();
        Etudiant etud = optEtud.get();

        Optional<Reaction> existingReaction = reactionRepository.findByPublicationAndEtudiant(pub, etud);

        if (existingReaction.isPresent()) {
            reactionRepository.delete(existingReaction.get());
        } else {
            Reaction reaction = new Reaction(pub, etud);
            reactionRepository.save(reaction);
        }

        long count = reactionRepository.countByPublication(pub);
        pub.setNbLikes((int) count);
        publicationRepository.save(pub);
    }
}
