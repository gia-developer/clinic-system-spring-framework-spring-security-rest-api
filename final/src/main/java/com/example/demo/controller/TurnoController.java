package com.example.demo.controller;

import com.example.demo.model.PacienteDTO;
import com.example.demo.model.TurnoDTO;
import com.example.demo.service.TurnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    TurnoServiceImpl turnoService;

    @Autowired
    public TurnoController(TurnoServiceImpl turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    //ResponseEntity: es para estados. No devuelve un paciente, sino que devolverá un estado con HttpStatus
    public ResponseEntity<?> crearTurno(@RequestBody TurnoDTO turnoDTO) {
        turnoService.crear(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //READ
    @GetMapping
    public Collection<TurnoDTO> verTurnos() {
        return turnoService.buscarTodos();
    }

    //READ
    @GetMapping("/{id}")
    public TurnoDTO obtenerTurnoPorId(@PathVariable Long id) {
        return turnoService.buscarPorId(id);
    }

    //UPDATE
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turnoDTO) {
        turnoService.actualizar(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Turno eliminado");
    }
}
