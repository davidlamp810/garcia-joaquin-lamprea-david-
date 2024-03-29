package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteDto) {
        LOGGER.info("PacienteEntradaDto: {}", JsonPrinter.toString(pacienteDto));
        Paciente pacienteEntidad = modelMapper.map(pacienteDto, Paciente.class);
        Paciente pacienteEntidadConId = pacienteRepository.save(pacienteEntidad);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteEntidadConId, PacienteSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: {}", JsonPrinter.toString(pacienteSalidaDto));
        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        List<PacienteSalidaDto> pacientesSalidaDto = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
                .collect(Collectors.toList());
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientesSalidaDto));
        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
        PacienteSalidaDto pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
        LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        return pacienteEncontrado;
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con ID: {}", id);
        } else {
            throw new ResourceNotFoundException("Paciente no encontrado con ID: " + id);
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteDto, Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID: " + id);
        }

        Paciente pacienteRecibido = modelMapper.map(pacienteDto, Paciente.class);
        pacienteRecibido.setId(id);
        Paciente pacienteActualizado = pacienteRepository.save(pacienteRecibido);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteActualizado, PacienteSalidaDto.class);
        LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));
        return pacienteSalidaDto;
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));

        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}
