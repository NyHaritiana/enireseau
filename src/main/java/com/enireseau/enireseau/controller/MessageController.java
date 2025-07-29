package com.enireseau.enireseau.controller;

import com.enireseau.enireseau.entites.Message;
import com.enireseau.enireseau.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void cree(@RequestBody Message message) {
        this.messageService.cree(message);
    }

    @GetMapping
    public List<Message> liste() {
        return this.messageService.liste();
    }

    @GetMapping("discussion/{etu1}/{etu2}")
    public List<Message> discussion(@PathVariable int etu1, @PathVariable int etu2) {
        return this.messageService.getDiscussion(etu1, etu2);
    }

    @DeleteMapping("{id_message}")
    public void supprime(@PathVariable int id_message) {
        this.messageService.supprime(id_message);
    }

    @PutMapping("{id_message}")
    public void modifie(@PathVariable int id_message, @RequestBody Message message) {
        this.messageService.modifie(id_message, message);
    }
}
