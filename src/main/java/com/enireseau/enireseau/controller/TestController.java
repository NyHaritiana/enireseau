package com.enireseau.enireseau.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "test")
public class TestController {

    @GetMapping(path = "string")
    public String getString(){
        return "Hello world";
    }

    @GetMapping
    public List<String> getList(){
        return List.of("Chaine1", "Chaine2", "Chaine3");
    }
}
