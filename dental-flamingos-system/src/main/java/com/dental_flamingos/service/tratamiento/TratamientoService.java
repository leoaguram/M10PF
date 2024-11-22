package com.dental_flamingos.service.tratamiento;

import com.dental_flamingos.model.CatalogoStatus;
import com.dental_flamingos.model.Tratamiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TratamientoService {
    List<Tratamiento> getTratamientoPageable(int page, int size, String dirSort, String sort);
    Tratamiento guardar(Tratamiento tratamiento);
    List<Tratamiento> listaTratamientos();
    boolean deleteTratamiento(Integer id);
    Tratamiento updateTratamiento(Tratamiento tratamiento);
    Optional<Tratamiento> getById(Integer id);
}
