package com.dental_flamingos.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<DetalleError> errorDeRestriccion(PacienteNotFoundException e, HttpServletRequest request) {
        DetalleError detalleError = new DetalleError();
        detalleError.setMensaje(e.getMessage());
        detalleError.setDetalle("Error de catalogo de Estado");
        detalleError.setStatusCode(HttpStatus.CONFLICT.toString());
        detalleError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalleError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> errorFormatoDeCliente(HttpMessageNotReadableException e) {
        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("mensaje", "El formato de los datos es incorrecto");
        detalles.put("detalle", e.getMessage());
        detalles.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratamientoValidacion(MethodArgumentNotValidException e) {
        HashMap<String, Object> detalles = new HashMap<>();
        detalles.put("mensaje", "Error de validación de campos");
        detalles.put("statusCode", e.getStatusCode().toString());
        detalles.put("timestamp", LocalDateTime.now().toString());
        HashMap<String, String> detalleCampos = new HashMap<>();

        for (FieldError campoError: e.getBindingResult().getFieldErrors()) {
            detalleCampos.put(campoError.getField(), campoError.getDefaultMessage());
        }
        detalles.put("errors", detalleCampos);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<DetalleError> recursoNoExiste(NoResourceFoundException e, HttpServletRequest request) {
        DetalleError detalleError = new DetalleError();
        detalleError.setMensaje("Ese recurso no existe: "+request.getRequestURI());
        detalleError.setTimestamp(LocalDateTime.now());
        detalleError.setStatusCode(HttpStatus.NOT_FOUND.toString());
        detalleError.setDetalle(request.getRequestURI()+"- "+request.getContextPath());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalleError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DetalleError> errorConversion(MethodArgumentTypeMismatchException e) {
        DetalleError detalleError = new DetalleError();
        detalleError.setMensaje(e.getMessage());
        detalleError.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        detalleError.setTimestamp(LocalDateTime.now());
        detalleError.setDetalle("Propiedad: "+e.getPropertyName() + ", Tipo de dato: "+ e.getRequiredType());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalleError);
    }
}
