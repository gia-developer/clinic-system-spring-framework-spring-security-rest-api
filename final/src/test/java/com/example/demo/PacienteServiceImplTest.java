package com.example.demo;

import com.example.demo.model.PacienteDTO;
import com.example.demo.service.IModelService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceImplTest {
    @Autowired
    private IModelService<PacienteDTO> modelService;

    public PacienteServiceImplTest() {
    }

    public PacienteDTO cargarPacienteDatos() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(1L);
        pacienteDTO.setNombre("Gianna");
        pacienteDTO.setApellido("Russo");
        pacienteDTO.setDni(42543617);
        pacienteDTO.setDomicilio("Calle 431 bis");
        pacienteDTO.setFechaDeAlta(LocalDate.of(2020, 04, 04));
        return pacienteDTO;
    }

    PacienteDTO paciente = cargarPacienteDatos();

    @Test
    public void crearPacienteTest() {
        modelService.crear(paciente);
        Assert.assertNotNull(this.modelService.buscarPorId(paciente.getId()));
    }

    @Test
    public void eliminarPacienteTest() {
        modelService.eliminar(1L);
        Assert.assertTrue(this.modelService.buscarTodos().isEmpty());
    }

    @Test
    public void traerPacienteTodos() {
        modelService.crear(paciente);
        Collection<PacienteDTO> pacienteDTOS = this.modelService.buscarTodos();
        Assert.assertTrue(!pacienteDTOS.isEmpty());
        Assert.assertTrue(pacienteDTOS.size() == 1);
        System.out.println(pacienteDTOS);
    }
}