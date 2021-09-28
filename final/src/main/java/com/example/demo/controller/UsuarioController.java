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

    @GetMapping("/lista-pacientes")
    public String pacientes() {
        return "pacientes";
    }

    @GetMapping("/lista-odontologos")
    public String odontologos() {
        return "odontologos";
    }

    @GetMapping("/lista-turnos")
    public String turnos() {
        return "turnos";
    }
}
