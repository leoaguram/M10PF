package com.dental_flamingos.repository;

import com.dental_flamingos.model.HistoricoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricoCitaRepository extends JpaRepository<HistoricoCita, Integer> {

}
