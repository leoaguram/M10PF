package com.dental_flamingos.mapper;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.model.*;
import com.dental_flamingos.repository.CatalogoStatusRepository;
import com.dental_flamingos.repository.DentistaRepository;
import com.dental_flamingos.repository.PacienteRepository;
import com.dental_flamingos.repository.TratamientoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class CitaMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TratamientoRepository tratamientoRepository;

    @Autowired
    DentistaRepository dentistaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    CatalogoStatusRepository statusRepository;

    public CitaDTO toDto(Cita cita) {
        CitaDTO citaDTO = modelMapper.map(cita, CitaDTO.class);
        if (cita.getDentista() != null) citaDTO.setNombreDentista(cita.getDentista().getNombre());
        if (cita.getPaciente() != null) citaDTO.setNombrePaciente(cita.getPaciente().getNombre());
        if (cita.getStatus() != null) citaDTO.setStatus(cita.getStatus().getDescripcion());
        if (cita.getTratamiento() != null) citaDTO.setTratamiento(cita.getTratamiento().getDescripcion());
        return citaDTO;
    }

    public Cita toEntity(CitaDTO citaDTO) throws ParseException, PacienteNotFoundException {
        Cita cita = modelMapper.map(citaDTO, Cita.class);

        if (citaDTO.getNombreDentista() != null && !citaDTO.getNombreDentista().isBlank()){
            Dentista dentista = dentistaRepository.findByNombre(citaDTO.getNombreDentista());
            cita.setDentista(dentista);
        }
        if (citaDTO.getNombrePaciente() != null && !citaDTO.getNombrePaciente().isBlank()) {
            Paciente paciente = pacienteRepository.findByNombre(citaDTO.getNombrePaciente());
            if (paciente == null) {
                throw new PacienteNotFoundException("El paciente no existe, intente registrar primero al paciente");
            }
            cita.setPaciente(paciente);
        }
        if (citaDTO.getStatus() != null && !citaDTO.getStatus().isBlank()) {
            CatalogoStatus status = statusRepository.findByDescripcion(citaDTO.getStatus());
            cita.setStatus(status);
        }
        if (citaDTO.getTratamiento() != null && !citaDTO.getTratamiento().isBlank()) {
            Tratamiento tratamiento = tratamientoRepository.findByDescripcion(citaDTO.getTratamiento());
            System.out.println(tratamiento);
            cita.setTratamiento(tratamiento);
        }
        return cita;
    }
}
