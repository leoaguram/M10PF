package com.dental_flamingos.dto;

import java.time.LocalDateTime;

public class CitaDTO {
    private Long idCita;
    private String nombrePaciente;
    private String nombreDentista;
    private String tratamiento;
    private String status;
    private LocalDateTime fechaHoraCita;
    private String observaciones;

    public CitaDTO() {
    }

    public CitaDTO(Long idCita, String nombrePaciente, String nombreDentista,
                   String tratamiento, String status, LocalDateTime fechaHoraCita,
                   String observaciones) {
        this.idCita = idCita;
        this.nombrePaciente = nombrePaciente;
        this.nombreDentista = nombreDentista;
        this.tratamiento = tratamiento;
        this.status = status;
        this.fechaHoraCita = fechaHoraCita;
        this.observaciones = observaciones;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreDentista() {
        return nombreDentista;
    }

    public void setNombreDentista(String nombreDentista) {
        this.nombreDentista = nombreDentista;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}