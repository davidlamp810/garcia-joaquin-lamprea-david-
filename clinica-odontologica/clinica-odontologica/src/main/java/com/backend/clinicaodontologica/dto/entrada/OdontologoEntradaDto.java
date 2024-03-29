package com.backend.clinicaodontologica.dto.entrada;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @NotNull(message = "La matrícula no puede ser nula")
    @NotBlank(message = "Debe especificarse la matrícula del odontólogo")
    @Size(min = 10, max = 50, message = "La matrícula debe tener entre 10 y 50 caracteres")
    private String matricula;

    @Size(min = 2, max = 50, message = "El nombre del odontólogo debe tener entre 2 y 50 caracteres")
    @NotNull(message = "El nombre del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    private String nombre;

    @Size(min = 2, max = 50, message = "El apellido del odontólogo debe tener entre 2 y 50 caracteres")
    @NotNull(message = "El apellido del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
