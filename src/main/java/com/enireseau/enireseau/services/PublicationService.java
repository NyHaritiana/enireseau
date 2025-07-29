package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Publication;
import com.enireseau.enireseau.repository.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    private PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public void creer(Publication publication) {
        this.publicationRepository.save(publication);
    }

    public List<Publication> liste() {
        return this.publicationRepository.findAll();
    }

    public void supprimer(int id_pub) {
        this.publicationRepository.deleteById(id_pub);
    }

    public Publication list(int id_pub){
        Optional<Publication> optionalPublication = this.publicationRepository.findById(id_pub);
        return optionalPublication.orElse(null);
    }
    public void modifier(int id_pub, Publication publication) {
        Publication publication1 = this.list(id_pub);
        if (publication1 != null){
            publication1.setReact_pub(publication.getReact_pub());
            publication1.setDescri_pub(publication.getDescri_pub());
            publication1.setEtudiant(publication.getEtudiant());
            this.publicationRepository.save(publication1);
        }
    }
}
