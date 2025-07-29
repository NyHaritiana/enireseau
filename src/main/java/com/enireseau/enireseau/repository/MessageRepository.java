package com.enireseau.enireseau.repository;

import com.enireseau.enireseau.entites.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByExpediteurAndDestinataire(int expediteur, int destinataire);
}
