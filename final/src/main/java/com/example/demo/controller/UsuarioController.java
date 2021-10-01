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

    @GetMapping("/pacientes.html")
    public String pacientes() {
        return "pacientes";
    }

    @GetMapping("/buscar-pacientes.html")
    public String buscarPacientes() {
        return "buscar-pacientes";
    }

    @GetMapping("/odontologos.html")
    public String odontologos() {
        return "odontologos";
    }

    @GetMapping("/buscar-odontologos.html")
    public String buscarOdontologos() {
        return "buscar-odontologos";
    }

    @GetMapping("/turnos.html")
    public String turnos() {
        return "turnos";
    }

    @GetMapping("/buscar-turnos.html")
    public String buscarTurnos() {
        return "buscar-turnos";
    }
}
