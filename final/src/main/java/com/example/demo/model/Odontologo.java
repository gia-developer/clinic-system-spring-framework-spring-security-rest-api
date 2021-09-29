package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "Odontologos")
@Entity
public class Odontologo {
    @Id
    @GeneratedValue
    private Long id;
    private String apellido, nombre, matricula;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos;
}