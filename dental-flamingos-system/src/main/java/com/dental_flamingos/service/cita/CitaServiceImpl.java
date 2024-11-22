package com.dental_flamingos.service.cita;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.mapper.CitaMapper;
import com.dental_flamingos.model.*;
import com.dental_flamingos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService{
    @Autowired
    CitaRepository citaRepository;

    @Autowired
    DentistaRepository dentistaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    CatalogoStatusRepository statusRepository;

    @Autowired
    TratamientoRepository tratamientoRepository;

    @Autowired
    CitaMapper citaMapper;

    @Override
    public List<CitaDTO> buscarCitas() {
        List<Cita> citas = citaRepository.findAll();
        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> getCitasPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Cita> pageResult = citaRepository.findAll(pageRequest);
        return pageResult.stream().map(citaMapper::toDto).toList();
    }

    @Override
    public CitaDTO guardar(Cita cita) throws ParseException, PacienteNotFoundException {
        Cita citaGuardada = citaRepository.save(cita);
        return citaMapper.toDto(citaGuardada);
    }

    @Override
    public List<CitaDTO> getCitasHoy(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);
        return citaRepository.findCitasByDate(startOfDay, endOfDay).stream().map(citaMapper::toDto).toList();
    }

    @Override
    public boolean deleteCita(Integer id) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            citaRepository.delete(cita.get());
            return true;
        } else
            return false;
    }

    @Override
    public CitaDTO updateCita(Cita cita) throws ParseException, PacienteNotFoundException {
        Cita citaUpdated = citaRepository.save(cita);
        return citaMapper.toDto(citaUpdated);
    }

    @Override
    public Optional<CitaDTO> getByIdDto(Integer id) {
        Optional<Cita> citaBD = citaRepository.findById(id);
        if (citaBD.isPresent()) {
            return Optional.of(citaMapper.toDto(citaBD.get()));
        } else
            return Optional.empty();
    }

    @Override
    public Optional<Cita> getByIdEnt(Integer id) {
        Optional<Cita> citaBD = citaRepository.findById(id);
        if (citaBD.isPresent()) {
            return Optional.of(citaBD.get());
        } else
            return Optional.empty();
    }

}
