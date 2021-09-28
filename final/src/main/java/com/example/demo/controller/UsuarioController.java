package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    //READ
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/lista-odontologos")
    public String odontologos() {
        return "odontologos";
    }
}
