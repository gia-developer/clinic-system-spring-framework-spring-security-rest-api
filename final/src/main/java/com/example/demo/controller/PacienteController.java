package com.example.demo.controller;

import com.example.demo.model.PacienteDTO;
import com.example.demo.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    PacienteServiceImpl pacienteService;

    @Autowired
    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    //ResponseEntity: es para estados. No devuelve un paciente, sino que devolverá un estado con HttpStatus
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDTO pacienteDTO) {
        pacienteService.crear(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //READ
    @GetMapping
    public Collection<PacienteDTO> verPacientes() {
        return pacienteService.buscarTodos();
    }

    //READ
    @GetMapping("/{id}")
    public PacienteDTO obtenerPacientesPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    //UPDATE
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        pacienteService.actualizar(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente eliminado");
    }
}
