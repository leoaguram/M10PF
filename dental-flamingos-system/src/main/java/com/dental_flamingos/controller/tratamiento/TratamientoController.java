package com.dental_flamingos.controller.tratamiento;

import com.dental_flamingos.model.Tratamiento;
import com.dental_flamingos.service.tratamiento.TratamientoService;
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
@RequestMapping(value = "tratamientos")
public class TratamientoController {
    @Autowired
    TratamientoService tratamientoService;

    @GetMapping("/")
    public ResponseEntity<List<Tratamiento>> listaTratamiento(){
        return ResponseEntity.status(HttpStatus.OK).body(tratamientoService.listaTratamientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> listaTratamientoById(@PathVariable Integer id){
        Optional<Tratamiento> alumnoDto = tratamientoService.getById(id);
        if (alumnoDto.isPresent()) {
            return ResponseEntity.ok(alumnoDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Tratamiento> salvarTratamiento(@Valid @RequestBody Tratamiento tratamiento) throws URISyntaxException {
        Tratamiento tratamiento1 = tratamientoService.guardar(tratamiento);
        URI location = new URI("/dentalflamingos/tratamientos/"+tratamiento1.getIdTratamiento());
        return ResponseEntity.created(location).body(tratamiento1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTratamiento(@PathVariable Integer id) {
        if (tratamientoService.deleteTratamiento(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> modificarTrat(@PathVariable Integer id, @RequestBody Tratamiento tratamiento) {
        tratamiento.setIdTratamiento(id);
        Tratamiento tratamientoUpdated = tratamientoService.updateTratamiento(tratamiento);
        return ResponseEntity.ok(tratamientoUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tratamiento> actualizacionParcialDto(@PathVariable Integer id, @RequestBody Tratamiento tratamiento) {
        Optional<Tratamiento> tratamientoBD = tratamientoService.getById(id);
        if (tratamientoBD.isPresent()) {
            Tratamiento modificable = tratamientoBD.get();

            if (tratamiento.getIdTratamiento() != null) modificable.setIdTratamiento(tratamiento.getIdTratamiento());
            if (tratamiento.getDescripcion() != null) modificable.setDescripcion(tratamiento.getDescripcion());

            return ResponseEntity.ok(tratamientoService.updateTratamiento(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
