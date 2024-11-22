package com.dental_flamingos.service.paciente;

import com.dental_flamingos.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> getPacientesPageable(int page, int size, String dirSort, String sort);
    Paciente guardar(Paciente paciente);
    List<Paciente> listaPacientes();
    Long getTotalPacientes();
    List<Paciente> getAll();
    boolean deletePaciente(Integer id);
    Paciente updatePaciente(Paciente paciente);
    Optional<Paciente> getById(Integer id);
}
