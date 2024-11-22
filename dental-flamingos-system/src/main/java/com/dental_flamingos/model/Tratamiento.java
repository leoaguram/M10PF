package com.dental_flamingos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "catalogotratamientos")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTratamiento;
    @NotEmpty(message = "El campo no puede estar vacío")
    @Size(min = 1, message = "El campo debe tener texto")
    private String descripcion;

    public Tratamiento() {
    }

    public Tratamiento(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tratamiento{" +
                "idTratamiento=" + idTratamiento +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

