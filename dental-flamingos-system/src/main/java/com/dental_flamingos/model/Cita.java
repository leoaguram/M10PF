package com.dental_flamingos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "idDentista")
    private Dentista dentista;

    @ManyToOne
    @JoinColumn(name = "idTratamiento")
    private Tratamiento tratamiento;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private CatalogoStatus status;

    @Column(name = "fecha_hora_cita")
    private LocalDateTime fechaHoraCita;

    private String observaciones;

    public Cita() {
    }

    public Cita(Paciente paciente, Dentista dentista, Tratamiento tratamiento,
                CatalogoStatus status, LocalDateTime fechaHoraCita, String observaciones) {
        this.paciente = paciente;
        this.dentista = dentista;
        this.tratamiento = tratamiento;
        this.status = status;
        this.fechaHoraCita = fechaHoraCita;
        this.observaciones = observaciones;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public CatalogoStatus getStatus() {
        return status;
    }

    public void setStatus(CatalogoStatus catalogoStatus) {
        this.status = catalogoStatus;
    }

    public LocalDateTime getFechaHoraCita() {
        return fechaHoraCita;
    }

    public void setFechaHoraCita(LocalDateTime fechaHoraCita) {
        this.fechaHoraCita = fechaHoraCita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", paciente=" + paciente +
                ", dentista=" + dentista +
                ", tratamiento=" + tratamiento +
                ", status=" + status +
                ", fechaHoraCita=" + fechaHoraCita +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
