package com.example.demo.repository;

import com.example.demo.model.Paciente;
import com.example.demo.model.Turno;
import com.example.demo.model.TurnoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    @Query("FROM Turno t WHERE t.id = ?1")
    Set<Turno> encontrarPorId(Long id);
}
