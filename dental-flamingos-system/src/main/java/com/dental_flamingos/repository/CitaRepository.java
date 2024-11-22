package com.dental_flamingos.repository;

import com.dental_flamingos.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    @Query("SELECT c FROM Cita c WHERE c.fechaHoraCita BETWEEN :startOfDay AND :endOfDay")
    List<Cita> findCitasByDate(@Param("startOfDay") LocalDateTime startOfDay,
                               @Param("endOfDay") LocalDateTime endOfDay);
}
