package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoDto) {
        Odontologo odontologo = modelMapper.map(odontologoDto, Odontologo.class);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontólogo registrado: {}", odontologoGuardado);
        return modelMapper.map(odontologoGuardado, OdontologoSalidaDto.class);
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo no encontrado con ID: " + id));
        LOGGER.info("Odontólogo encontrado: {}", odontologo);
        return modelMapper.map(odontologo, OdontologoSalidaDto.class);
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        LOGGER.info("Listado de todos los odontólogos");
        return odontologos.stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (odontologoRepository.existsById(id)) {
            odontologoRepository.deleteById(id);
            LOGGER.info("Odontólogo eliminado con ID: {}", id);
        } else {
            throw new ResourceNotFoundException("Odontólogo no encontrado con ID: " + id);
        }
    }

    @Override
    public OdontologoSalidaDto modificarOdontologo(OdontologoEntradaDto odontologoDto, Long id) {
        if (!odontologoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con ID: " + id);
        }

        Odontologo odontologo = modelMapper.map(odontologoDto, Odontologo.class);
        odontologo.setId(id);
        Odontologo odontologoActualizado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontólogo actualizado: {}", odontologoActualizado);
        return modelMapper.map(odontologoActualizado, OdontologoSalidaDto.class);
    }
}
