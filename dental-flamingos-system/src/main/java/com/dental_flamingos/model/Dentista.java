package com.dental_flamingos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dentistas")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDentista;
    private String nombre;
    @Column(name = "numero_celular")
    private String numeroCelular;
    private String usuario;
    @Column(name = "dent_password")
    private String password;

    public Dentista() {
    }

    public Dentista(String nombre, String numeroCelular, String usuario, String dent_password) {
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.usuario = usuario;
        this.password = dent_password;
    }

    // Getters y Setters
    public Integer getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(Integer idDentista) {
        this.idDentista = idDentista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "idDentista=" + idDentista +
                ", nombre='" + nombre + '\'' +
                ", numeroCelular='" + numeroCelular + '\'' +
                ", usuario='" + usuario + '\'' +
                ", dent_password='" + password + '\'' +
                '}';
    }
}
