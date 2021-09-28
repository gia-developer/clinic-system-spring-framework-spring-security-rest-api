package com.example.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Paciente paciente;
    private Odontologo odontologo;
}
