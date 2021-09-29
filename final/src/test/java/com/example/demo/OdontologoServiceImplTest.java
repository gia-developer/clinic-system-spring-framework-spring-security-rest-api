package com.example.demo;

import com.example.demo.model.OdontologoDTO;
import com.example.demo.service.IModelService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceImplTest {
    @Autowired
    private IModelService<OdontologoDTO> modelService;

    public OdontologoServiceImplTest() {
    }

    public OdontologoDTO cargarOdontologoDatos() {
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setId(1L);
        odontologoDTO.setMatricula("123A");
        odontologoDTO.setNombre("Gianna");
        odontologoDTO.setApellido("Russo");
        return odontologoDTO;
    }

    OdontologoDTO odontologo = cargarOdontologoDatos();

    @Test
    public void crearOdontologoTest() {
        modelService.crear(odontologo);
        Assert.assertNotNull(this.modelService.buscarPorId(odontologo.getId()));
    }

    @Test
    public void eliminarOdontologoTest() {
        modelService.eliminar(1L);
        Assert.assertTrue(this.modelService.buscarTodos().isEmpty());
    }

    @Test
    public void traerOdontologoTodos() {
        modelService.crear(odontologo);
        Collection<OdontologoDTO> odontologoDTOS = this.modelService.buscarTodos();
        Assert.assertTrue(!odontologoDTOS.isEmpty());
        Assert.assertTrue(odontologoDTOS.size() == 1);
        System.out.println(odontologoDTOS);
    }
}