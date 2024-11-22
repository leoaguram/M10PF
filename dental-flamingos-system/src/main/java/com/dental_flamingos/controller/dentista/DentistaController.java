package com.dental_flamingos.controller.dentista;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.DentistaNotFoundException;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.model.CatalogoStatus;
import com.dental_flamingos.model.Dentista;
import com.dental_flamingos.service.dentista.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("dentistas")
public class DentistaController {
    @Autowired
    DentistaService dentistaService;

    @GetMapping("/")
    public List<Dentista> listaDentista(){
        return dentistaService.buscarDentistas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> listaStatusById(@PathVariable Integer id){
        Optional<Dentista> dentista = dentistaService.getById(id);
        if (dentista.isPresent()) {
            return ResponseEntity.ok(dentista.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentista> modificarDentista(@PathVariable Integer id, @RequestBody Dentista dentista) throws ParseException, DentistaNotFoundException {
        dentista.setIdDentista(id);
        Dentista dentistaUpdated = dentistaService.updateDentista(dentista);
        return ResponseEntity.ok(dentistaUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Dentista> actualizacionParcial(@PathVariable Integer id, @RequestBody Dentista dentista) throws ParseException, DentistaNotFoundException {
        Optional<Dentista> dentistaBD = dentistaService.getById(id);
        if (dentistaBD.isPresent()) {
            Dentista modificable = dentistaBD.get();

            if (dentista.getIdDentista() != null) modificable.setIdDentista(dentista.getIdDentista());
            if (dentista.getNombre() != null) modificable.setNombre(dentista.getNombre());
            if (dentista.getUsuario() != null) modificable.setUsuario(dentista.getUsuario());
            if (dentista.getPassword() != null) modificable.setPassword(dentista.getPassword());
            if (dentista.getNumeroCelular() != null) modificable.setNumeroCelular(dentista.getNumeroCelular());

            return ResponseEntity.ok(dentistaService.updateDentista(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
