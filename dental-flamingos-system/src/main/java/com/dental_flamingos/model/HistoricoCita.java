package com.dental_flamingos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historico_citas")
public class HistoricoCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoria;

    @OneToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    private Paciente paciente;

    @Column(name = "inicio_tratamiento")
    private Date inicioTratamiento;

    private String detalles;

    // Constructor
    public HistoricoCita() {
    }

    public HistoricoCita(Paciente paciente, Date inicioTratamiento, String detalles) {
        this.paciente = paciente;
        this.inicioTratamiento = inicioTratamiento;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Integer getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Integer idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getInicioTratamiento() {
        return inicioTratamiento;
    }

    public void setInicioTratamiento(Date inicioTratamiento) {
        this.inicioTratamiento = inicioTratamiento;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "idHistoria=" + idHistoria +
                ", paciente=" + paciente.getNombre() +
                ", inicioTratamiento=" + inicioTratamiento +
                ", detalles='" + detalles + '\'' +
                '}';
    }
}
