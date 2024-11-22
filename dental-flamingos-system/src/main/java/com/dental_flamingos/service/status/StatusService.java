package com.dental_flamingos.service.status;

import com.dental_flamingos.model.CatalogoStatus;
import com.dental_flamingos.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<CatalogoStatus> getStatusPageable(int page, int size, String dirSort, String sort);

    List<CatalogoStatus> listStatus();
    CatalogoStatus guardar(CatalogoStatus status);
    boolean deleteStatus(Integer id);
    CatalogoStatus updateStatus(CatalogoStatus status);
    Optional<CatalogoStatus> getById(Integer id);
}
