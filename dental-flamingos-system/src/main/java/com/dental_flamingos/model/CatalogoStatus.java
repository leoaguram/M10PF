package com.dental_flamingos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "catalogostatus")
public class CatalogoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatus;
    @NotEmpty(message = "El campo no puede estar vacío")
    private String descripcion;

    public CatalogoStatus() {
    }

    public CatalogoStatus(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Status{" +
                "idStatus=" + idStatus +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

