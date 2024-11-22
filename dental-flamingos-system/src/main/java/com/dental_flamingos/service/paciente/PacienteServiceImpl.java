package com.dental_flamingos.service.paciente;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.model.CatalogoStatus;
import com.dental_flamingos.model.Cita;
import com.dental_flamingos.model.Paciente;
import com.dental_flamingos.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> getPacientesPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Paciente> pageResult = pacienteRepository.findAll(pageRequest);
        return pageResult.stream().toList();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        paciente.setIdPaciente(null);
        pacienteRepository.save(paciente);
        return paciente;
    }

    @Override
    public List<Paciente> listaPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Long getTotalPacientes() {
        return pacienteRepository.count();
    }

    @Override
    public List<Paciente> getAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public boolean deletePaciente(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            pacienteRepository.delete(paciente.get());
            return true;
        } else
            return false;
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) {
        Paciente pacienteUpdated = pacienteRepository.save(paciente);
        return pacienteUpdated;
    }

    @Override
    public Optional<Paciente> getById(Integer id) {
        Optional<Paciente> pacienteBD = pacienteRepository.findById(id);
        if (pacienteBD.isPresent()) {
            return Optional.of(pacienteBD.get());
        } else
            return Optional.empty();
    }


}
