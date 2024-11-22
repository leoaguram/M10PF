package com.dental_flamingos.service.dentista;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.DentistaNotFoundException;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.model.Dentista;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface DentistaService {
    List<Dentista> buscarDentistas();
    Dentista updateDentista(Dentista dentista) throws ParseException, DentistaNotFoundException;
    Optional<Dentista> getById(Integer id);
}
