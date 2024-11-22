package com.dental_flamingos.repository;

import com.dental_flamingos.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Integer> {
    Tratamiento findByDescripcion(String descripcion);
}
