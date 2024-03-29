package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OdontologoServiceTest {

    @Mock
    private OdontologoRepository odontologoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OdontologoService odontologoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registrarOdontologo_WhenValidInput_ReturnsOdontologoSalidaDto() {
        // Arrange
        OdontologoEntradaDto entradaDto = new OdontologoEntradaDto();
        entradaDto.setNombre("John");
        entradaDto.setApellido("Doe");

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");

        when(modelMapper.map(entradaDto, Odontologo.class)).thenReturn(odontologo);
        when(odontologoRepository.save(odontologo)).thenReturn(odontologo);

        // Act
        OdontologoSalidaDto salidaDto = odontologoService.registrarOdontologo(entradaDto);

        // Assert
        assertEquals("John", salidaDto.getNombre());
        assertEquals("Doe", salidaDto.getApellido());
    }

    @Test
    void buscarOdontologoPorId_WhenValidId_ReturnsOdontologoSalidaDto() {
        // Arrange
        Long id = 1L;
        Odontologo odontologo = new Odontologo();
        odontologo.setId(id);
        odontologo.setNombre("John");
        odontologo.setApellido("Doe");

        when(odontologoRepository.findById(id)).thenReturn(Optional.of(odontologo));

        // Act
        OdontologoSalidaDto salidaDto = odontologoService.buscarOdontologoPorId(id);

        // Assert
        assertEquals(id, salidaDto.getId());
        assertEquals("John", salidaDto.getNombre());
        assertEquals("Doe", salidaDto.getApellido());
    }

    @Test
    void buscarOdontologoPorId_WhenInvalidId_ThrowsResourceNotFoundException() {
        // Arrange
        Long id = 1L;

        when(odontologoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarOdontologoPorId(id));
    }

}
