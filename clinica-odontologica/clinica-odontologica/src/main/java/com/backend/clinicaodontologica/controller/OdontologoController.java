package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaodontologica.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // POST

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@Valid @RequestBody OdontologoEntradaDto odontologo) {
        OdontologoSalidaDto nuevoOdontologo = odontologoService.registrarOdontologo(odontologo);
        return new ResponseEntity<>(nuevoOdontologo, HttpStatus.CREATED);
    }

    // PUT

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@Valid @RequestBody OdontologoEntradaDto odontologo, @PathVariable Long id) {
        OdontologoSalidaDto odontologoActualizado = odontologoService.actualizarOdontologo(odontologo, id);
        return new ResponseEntity<>(odontologoActualizado, HttpStatus.OK);
    }

    // GET

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoSalidaDto> obtenerOdontologoPorId(@PathVariable Long id) {
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(id);
        return new ResponseEntity<>(odontologo, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologos();
        return new ResponseEntity<>(odontologos, HttpStatus.OK);
    }

    // DELETE

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}

