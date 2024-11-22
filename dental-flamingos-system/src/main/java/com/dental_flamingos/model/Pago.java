package com.dental_flamingos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngreso;

    @ManyToOne
    @JoinColumn(name = "idCita")
    private Cita cita;

    private Double monto;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private CatalogoStatus status;

    @Column(name = "monto_restante")
    private Double montoRestante;

    @Column(name = "monto_pagado")
    private Double montoPagado;

    public Pago() {
    }

    public Pago(Cita cita, Double monto, CatalogoStatus status, Double montoRestante) {
        this.cita = cita;
        this.monto = monto;
        this.status = status;
        this.montoRestante = montoRestante;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public CatalogoStatus getStatus() {
        return status;
    }

    public void setStatus(CatalogoStatus catalogoStatus) {
        this.status = catalogoStatus;
    }

    public Double getMontoRestante() {
        return montoRestante;
    }

    public void setMontoRestante(Double montoRestante) {
        this.montoRestante = montoRestante;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idIngreso=" + idIngreso +
                ", cita=" + cita.getPaciente().getNombre() +
                ", monto=" + monto +
                ", status=" + status.getDescripcion() +
                ", montoRestante=" + montoRestante +
                '}';
    }
}
