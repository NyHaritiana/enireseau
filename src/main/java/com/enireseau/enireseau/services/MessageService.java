package com.enireseau.enireseau.services;

import com.enireseau.enireseau.entites.Message;
import com.enireseau.enireseau.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message cree(Message message) {
        return this.messageRepository.save(message);
    }

    public List<Message> liste() {
        return this.messageRepository.findAll();
    }

    public Message get(int id_message) {
        return this.messageRepository.findById(id_message).orElse(null);
    }

    public void supprime(int id_message) {
        this.messageRepository.deleteById(id_message);
    }

    public void modifie(int id_message, Message nouveau) {
        Message ancien = get(id_message);
        if (ancien != null && ancien.getId_message() == nouveau.getId_message()) {
            ancien.setExpediteur(nouveau.getExpediteur());
            ancien.setDestinataire(nouveau.getDestinataire());
            ancien.setContenu(nouveau.getContenu());
            ancien.setDate_envoi(nouveau.getDate_envoi());
            messageRepository.save(ancien);
        }
    }

    public List<Message> getDiscussion(int etu1, int etu2) {
        List<Message> messages1 = messageRepository.findByExpediteurAndDestinataire(etu1, etu2);
        List<Message> messages2 = messageRepository.findByExpediteurAndDestinataire(etu2, etu1);
        messages1.addAll(messages2);
        messages1.sort((a, b) -> a.getDate_envoi().compareTo(b.getDate_envoi()));
        return messages1;
    }
}
