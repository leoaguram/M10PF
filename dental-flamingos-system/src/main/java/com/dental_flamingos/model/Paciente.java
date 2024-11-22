package com.dental_flamingos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;
    @Column(name = "fecha_nac")
    @NotEmpty(message = "La fecha de nacimiento no puede estar vacía")
    private String fechaNac;
    @Column(name = "numero_celular")
    @NotEmpty(message = "El número telefónico no puede estar vacía")
    @Pattern(regexp = "^\\d{10}$", message = "El formato del número telefónico no es válido")
    private String numeroCelular;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "El correo no tiene el formato válido")
    private String email;
    @NotEmpty(message = "La dirección no puede estar vacía")
    private String direccion;

    public Paciente() {
    }

    public Paciente(String nombre, String fechaNac, String numeroCelular, String email, String direccion) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.numeroCelular = numeroCelular;
        this.email = email;
        this.direccion = direccion;
    }

    // Getters y Setters
    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", nombre='" + nombre + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", numeroCelular='" + numeroCelular + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}

