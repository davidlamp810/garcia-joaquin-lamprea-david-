package com.backend; // Asumiendo que el paquete es com.backend

public class Alumno {

    // Atributos
    private String nombre;

    // Constructor
    public Alumno(String nombre) {
        this.nombre = nombre;
    }

    // Métodos Getter y Setter para el atributo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

