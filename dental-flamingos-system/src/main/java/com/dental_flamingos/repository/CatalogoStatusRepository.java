package com.dental_flamingos.repository;

import com.dental_flamingos.model.CatalogoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoStatusRepository extends JpaRepository<CatalogoStatus, Integer> {
    CatalogoStatus findByDescripcion(String descripcion);
}
