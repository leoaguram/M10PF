package com.dental_flamingos.service.dentista;

import com.dental_flamingos.exception.DentistaNotFoundException;
import com.dental_flamingos.model.Cita;
import com.dental_flamingos.model.Dentista;
import com.dental_flamingos.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaServiceImpl implements DentistaService {
    @Autowired
    DentistaRepository dentistaRepository;

    @Override
    public List<Dentista> buscarDentistas() {
        return dentistaRepository.findAll();
    }

    @Override
    public Dentista updateDentista(Dentista dentista) throws ParseException, DentistaNotFoundException {
        Dentista dentistaUpdated = dentistaRepository.save(dentista);
        return dentistaUpdated;
    }

    @Override
    public Optional<Dentista> getById(Integer id) {
        Optional<Dentista> dentistaBD = dentistaRepository.findById(id);
        if (dentistaBD.isPresent()) {
            return Optional.of(dentistaBD.get());
        } else
            return Optional.empty();
    }

}
