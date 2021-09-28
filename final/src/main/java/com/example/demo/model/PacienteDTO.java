package com.example.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
//Información a transferir a las vistas
public class PacienteDTO {
    private Long id;
    private String apellido, nombre;
    private int dni;
    private String domicilio;
    private LocalDate fechaDeAlta;
}
