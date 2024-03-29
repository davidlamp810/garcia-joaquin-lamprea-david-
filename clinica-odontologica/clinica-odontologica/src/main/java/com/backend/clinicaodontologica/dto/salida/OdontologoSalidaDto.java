package com.backend.clinicaodontologica.dto.salida;

import java.util.Objects;

public class OdontologoSalidaDto {

    private Long id;
    private String matricula;
    private String nombre;
    private String apellido;

    public OdontologoSalidaDto() {
    }

    public OdontologoSalidaDto(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OdontologoSalidaDto that = (OdontologoSalidaDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(matricula, that.matricula) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellido, that.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, nombre, apellido);
    }
}
