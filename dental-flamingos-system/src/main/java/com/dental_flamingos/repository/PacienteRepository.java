package com.dental_flamingos.repository;

import com.dental_flamingos.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Paciente findByNombre(String nombre);


}
