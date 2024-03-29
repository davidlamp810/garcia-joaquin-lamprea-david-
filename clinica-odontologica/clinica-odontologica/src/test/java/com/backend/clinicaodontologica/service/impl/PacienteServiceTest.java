package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registrarPaciente_WhenValidInput_ReturnsPacienteSalidaDto() {
        // Arrange
        PacienteEntradaDto entradaDto = new PacienteEntradaDto();
        entradaDto.setNombre("John");
        entradaDto.setApellido("Doe");

        Paciente paciente = new Paciente();
        paciente.setNombre("John");
        paciente.setApellido("Doe");

        when(modelMapper.map(entradaDto, Paciente.class)).thenReturn(paciente);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        // Act
        PacienteSalidaDto salidaDto = pacienteService.registrarPaciente(entradaDto);

        // Assert
        assertEquals("John", salidaDto.getNombre());
        assertEquals("Doe", salidaDto.getApellido());
    }

    @Test
    void listarPacientes_WhenPacientesExist_ReturnsListOfPacienteSalidaDto() {
        // Arrange
        Paciente paciente = new Paciente();
        paciente.setNombre("John");
        paciente.setApellido("Doe");

        when(pacienteRepository.findAll()).thenReturn(Collections.singletonList(paciente));

        // Act
        List<PacienteSalidaDto> salidaDtoList = pacienteService.listarPacientes();

        // Assert
        assertFalse(salidaDtoList.isEmpty());
        assertEquals("John", salidaDtoList.get(0).getNombre());
        assertEquals("Doe", salidaDtoList.get(0).getApellido());
    }

    @Test
    void buscarPacientePorId_WhenValidId_ReturnsPacienteSalidaDto() {
        // Arrange
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setNombre("John");
        paciente.setApellido("Doe");

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        // Act
        PacienteSalidaDto salidaDto = pacienteService.buscarPacientePorId(id);

        // Assert
        assertEquals(id, salidaDto.getId());
        assertEquals("John", salidaDto.getNombre());
        assertEquals("Doe", salidaDto.getApellido());
    }

}
