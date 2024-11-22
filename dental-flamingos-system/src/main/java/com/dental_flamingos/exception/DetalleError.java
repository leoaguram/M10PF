package com.dental_flamingos.exception;

import java.time.LocalDateTime;

public class DetalleError {
    private String statusCode;
    private String mensaje;
    private String detalle;
    private LocalDateTime timestamp;

    public DetalleError(String statusCode, String mensaje, String detalle, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.mensaje = mensaje;
        this.detalle = detalle;
        this.timestamp = timestamp;
    }

    public DetalleError() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
