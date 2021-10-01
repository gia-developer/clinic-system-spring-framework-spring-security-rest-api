package com.example.demo.repository;

import com.example.demo.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query("FROM Paciente p WHERE p.id = ?1")
    Set<Odontologo> encontrarPorId(Long id);
}
