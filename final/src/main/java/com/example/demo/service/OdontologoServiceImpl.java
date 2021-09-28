package com.example.demo.service;

import com.example.demo.model.Odontologo;
import com.example.demo.model.OdontologoDTO;
import com.example.demo.model.Paciente;
import com.example.demo.model.PacienteDTO;
import com.example.demo.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoServiceImpl implements IModelService<OdontologoDTO> {
    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void crear(OdontologoDTO odontologoDTO) {
        Odontologo odontologo1 = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo1);
    }

    @Override
    public Collection<OdontologoDTO> buscarTodos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologoDTOS = new HashSet<>();
        for(Odontologo odontologo : odontologos) {
            odontologoDTOS.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }
        return odontologoDTOS;
    }

    @Override
    public OdontologoDTO buscarPorId(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if(odontologo.isPresent()) {
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        return odontologoDTO;
    }

    /*public Set<OdontologoDTO> obtenerOdontologoPorMatricula(String matricula){
        Set<Odontologo> odontologos =  odontologoRepository.encontrarPorMatricula(matricula);
        Set<OdontologoDTO> odontologoDTOS = new HashSet<>();
        for(Odontologo odontologo : odontologos){
            OdontologoDTO odontologoDTO = new OdontologoDTO();
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        return odontologoDTOS;
    }*/

    @Override
    public void actualizar(OdontologoDTO odontologoDTO){
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}
