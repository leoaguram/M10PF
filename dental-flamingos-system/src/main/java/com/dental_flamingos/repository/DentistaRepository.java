package com.dental_flamingos.repository;

import com.dental_flamingos.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, Integer> {
    Dentista findByNombre(String nombre);

}
