package com.dental_flamingos.service.cita;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.model.Cita;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<CitaDTO> buscarCitas();
    List<CitaDTO> getCitasPageable(int page, int size, String dirSort, String sort);
    CitaDTO guardar(Cita cita) throws ParseException, PacienteNotFoundException;
    List<CitaDTO> getCitasHoy(LocalDate date);
    boolean deleteCita(Integer id);
    CitaDTO updateCita(Cita cita) throws ParseException, PacienteNotFoundException;
    Optional<CitaDTO> getByIdDto(Integer id);
    Optional<Cita> getByIdEnt(Integer id);
}
