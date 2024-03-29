package com.backend.clinicaodontologica;

import com.backend.clinicaodontologica.controller.OdontologoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {

    @Autowired
    private OdontologoController odontologoController;

    @Test
    void contextLoads() {
        // Verificar que el contexto se cargue correctamente
    }

    @Test
    void odontologoControllerNotNull() {
        assertNotNull(odontologoController, "El controlador de odontólogos no debe ser nulo");
    }

    // Agrega más pruebas aquí para otros componentes de tu aplicación

}

