package com.dental_flamingos.controller.status;

import com.dental_flamingos.model.CatalogoStatus;
import com.dental_flamingos.model.Tratamiento;
import com.dental_flamingos.service.status.StatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping("/")
    public ResponseEntity<List<CatalogoStatus>> listaPaciente(){
        return ResponseEntity.status(HttpStatus.OK).body(statusService.listStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoStatus> listaStatusById(@PathVariable Integer id){
        Optional<CatalogoStatus> status = statusService.getById(id);
        if (status.isPresent()) {
            return ResponseEntity.ok(status.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<CatalogoStatus> salvarStatus(@Valid @RequestBody CatalogoStatus status) throws URISyntaxException {
       CatalogoStatus status1 = statusService.guardar(status);
        URI location = new URI("/dentalflamingos/status/"+status.getIdStatus());
        return ResponseEntity.created(location).body(status1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarStatus(@PathVariable Integer id) {
        if (statusService.deleteStatus(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoStatus> modificarStatus(@PathVariable Integer id, @RequestBody CatalogoStatus catalogoStatus) {
        catalogoStatus.setIdStatus(id);
        CatalogoStatus catalogoStatusUpdated = statusService.updateStatus(catalogoStatus);
        return ResponseEntity.ok(catalogoStatusUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CatalogoStatus> actualizacionParcial(@PathVariable Integer id, @RequestBody CatalogoStatus catalogoStatus) {
        Optional<CatalogoStatus> catalogoStatusBD = statusService.getById(id);
        if (catalogoStatusBD.isPresent()) {
            CatalogoStatus modificable = catalogoStatusBD.get();

            if (catalogoStatus.getIdStatus() != null) modificable.setIdStatus(catalogoStatus.getIdStatus());
            if (catalogoStatus.getDescripcion() != null) modificable.setDescripcion(catalogoStatus.getDescripcion());

            return ResponseEntity.ok(statusService.updateStatus(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
