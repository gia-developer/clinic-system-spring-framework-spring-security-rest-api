package com.example.demo.controller;

import com.example.demo.model.OdontologoDTO;
import com.example.demo.service.OdontologoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    OdontologoServiceImpl odontologoService;

    @Autowired
    public OdontologoController(OdontologoServiceImpl odontologoService) {
        this.odontologoService = odontologoService;
    }

    @Autowired
    ObjectMapper mapper;

    //CREATE
    @PostMapping
    //ResponseEntity: es para estados. No devuelve un paciente, sino que devolverá un estado con HttpStatus
    public ResponseEntity<?> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.crear(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //READ
    @GetMapping
    public Collection<OdontologoDTO> verOdontologos() {
        return odontologoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public OdontologoDTO obtenerOdontologosPorId(@PathVariable Long id) {
        return odontologoService.buscarPorId(id);
    }

    //UPDATE
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.actualizar(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Odontólogo eliminado");
    }
}
