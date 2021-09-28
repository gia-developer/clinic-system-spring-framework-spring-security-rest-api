package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Table(name = "Pacientes")
@Entity
public class Paciente {
    @Id
    @GeneratedValue
    private Long id;
    private String apellido, nombre;
    private int dni;
    private LocalDate fechaDeAlta;
    private String domicilio;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos;
}
