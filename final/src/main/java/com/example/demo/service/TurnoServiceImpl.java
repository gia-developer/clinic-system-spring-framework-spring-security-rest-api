package com.example.demo.service;

import com.example.demo.GlobalExceptionHandler;
import com.example.demo.model.Odontologo;
import com.example.demo.model.OdontologoDTO;
import com.example.demo.model.Turno;
import com.example.demo.model.TurnoDTO;
import com.example.demo.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TurnoServiceImpl implements IModelService<TurnoDTO> {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoServiceImpl(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Autowired
    ObjectMapper mapper;

    @Override
    public void crear(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        if(turno.getPaciente().equals("") && turno.getOdontologo().equals("") && turno.getFecha().equals("")) {
            System.out.println("No se pudo agregar");
        } else {
            if( turno.getFecha().isAfter(LocalDateTime.now()) ) turnoRepository.save(turno);
        }
    }

    @Override
    public Collection<TurnoDTO> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnoDTOS = new HashSet<>();
        for (Turno turno : turnos) {
            turnoDTOS.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return turnoDTOS;
    }

    public Collection<TurnoDTO> filtrar() {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnoDTOSFilter = new HashSet<>();
        for (Turno turno : turnos) {
            if (LocalDateTime.now().getDayOfMonth() + 7 >= turno.getFecha().getDayOfMonth()) {
                turnoDTOSFilter.add(mapper.convertValue(turno, TurnoDTO.class));
            }
        }
        return turnoDTOSFilter;
    }

    @Override
    public TurnoDTO buscarPorId(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        TurnoDTO turnoDTO = null;
        if(turno.isPresent()) {
            turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
        }
        return turnoDTO;
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        if(turno.getPaciente().equals("") && turno.getOdontologo().equals("") && turno.getFecha().equals("")) {
            System.out.println("No se pudo agregar");
        } else {
            if( turno.getFecha().isAfter(LocalDateTime.now()) ) turnoRepository.save(turno);
        }
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }
}
