package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Etudiant;
import com.enireseau.enireseau.entites.Image;
import com.enireseau.enireseau.entites.Publication;
import com.enireseau.enireseau.entites.Reaction;
import com.enireseau.enireseau.repository.EtudiantRepository;
import com.enireseau.enireseau.repository.ImageRepository;
import com.enireseau.enireseau.repository.PublicationRepository;
import com.enireseau.enireseau.repository.ReactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class PublicationService {

    private static final Logger logger = LoggerFactory.getLogger(PublicationService.class);

    private final PublicationRepository publicationRepository;
    private final EtudiantRepository etudiantRepository;
    private final ReactionRepository reactionRepository;

    private final ImageRepository imageRepository;

    public PublicationService(PublicationRepository publicationRepository, EtudiantRepository etudiantRepository, ReactionRepository reactionRepository, ImageRepository imageRepository) {
        this.publicationRepository = publicationRepository;
        this.etudiantRepository = etudiantRepository;
        this.reactionRepository = reactionRepository;
        this.imageRepository = imageRepository;
    }

    public void creerPublication(String descri_pub, String num_matr, List<MultipartFile> files) {
        try {
            Optional<Etudiant> etudiantOpt = etudiantRepository.findById(num_matr);
            if (etudiantOpt.isEmpty()) {
                logger.warn("Étudiant non trouvé pour le matricule: {}", num_matr);
                return;
            }

            Etudiant etudiant = etudiantOpt.get();
            Publication publication = new Publication();
            publication.setDescri_pub(descri_pub);
            publication.setEtudiant(etudiant);

            publicationRepository.save(publication);

            if (files != null && !files.isEmpty()) {
                Path uploadDir = Paths.get("uploads");
                Files.createDirectories(uploadDir);

                for (MultipartFile file : files) {
                    if (file.isEmpty()) continue;

                    String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path filePath = uploadDir.resolve(filename);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    Image image = new Image();
                    image.setChemin("uploads/" + filename);
                    image.setPublication(publication);
                    imageRepository.save(image);
                }
            }

        } catch (IOException e) {
            logger.error("Erreur d'I/O lors de l'enregistrement des images", e);
        } catch (Exception e) {
            logger.error("Erreur lors de la création de la publication", e);
        }
    }

    public List<Publication> liste() {
        try {
            return this.publicationRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des publications", e);
            return Collections.emptyList();
        }
    }

    public void supprimer(int id_pub) {
        try {
            this.publicationRepository.deleteById(id_pub);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de la publication id={}", id_pub, e);
        }
    }

    public Publication list(int id_pub) {
        try {
            return this.publicationRepository.findById(id_pub).orElse(null);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de la publication id={}", id_pub, e);
            return null;
        }
    }

    public void modifier(int id_pub, Publication publication) {
        try {
            Publication publication1 = this.list(id_pub);
            if (publication1 != null) {
                publication1.setDescri_pub(publication.getDescri_pub());

                if (publication.getEtudiant() != null && publication.getEtudiant().getNum_matr() != null) {
                    Optional<Etudiant> etuOpt = etudiantRepository.findById(publication.getEtudiant().getNum_matr());
                    etuOpt.ifPresent(publication1::setEtudiant);
                }

                this.publicationRepository.save(publication1);
            } else {
                logger.warn("Publication à modifier introuvable pour id={}", id_pub);
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la modification de la publication id={}", id_pub, e);
        }
    }

    public void toggleReaction(int id_pub, String num_matr) {
        try {
            Optional<Publication> optPub = publicationRepository.findById(id_pub);
            Optional<Etudiant> optEtud = etudiantRepository.findById(num_matr);

            if (optPub.isEmpty() || optEtud.isEmpty()) {
                logger.warn("Publication ou étudiant introuvable pour id_pub={} et num_matr={}", id_pub, num_matr);
                return;
            }

            Publication pub = optPub.get();
            Etudiant etud = optEtud.get();

            Optional<Reaction> existingReaction = reactionRepository.findByPublicationAndEtudiant(pub, etud);

            if (existingReaction.isPresent()) {
                reactionRepository.delete(existingReaction.get());
                logger.info("Réaction supprimée pour pub id={} et étudiant {}", id_pub, num_matr);
            } else {
                Reaction reaction = new Reaction(pub, etud);
                reactionRepository.save(reaction);
                logger.info("Réaction ajoutée pour pub id={} et étudiant {}", id_pub, num_matr);
            }

            long count = reactionRepository.countByPublication(pub);
            pub.setNbLikes((int) count);
            publicationRepository.save(pub);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout/suppression de la réaction", e);
        }
    }

}
