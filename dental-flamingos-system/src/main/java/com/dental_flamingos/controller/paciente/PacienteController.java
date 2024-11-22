package com.dental_flamingos.controller.paciente;

import com.dental_flamingos.model.CatalogoStatus;
import com.dental_flamingos.model.Paciente;
import com.dental_flamingos.service.paciente.PacienteService;
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
@RequestMapping(value = "pacientes")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @GetMapping("/")
    public ResponseEntity<List<Paciente>> listaPaciente(){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.listaPacientes());
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPacientes() {
        return ResponseEntity.ok(pacienteService.getTotalPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listaPacienteById(@PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.getById(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Paciente> salvarPaciente(@Valid @RequestBody Paciente paciente) throws URISyntaxException {
        Paciente paciente1 = pacienteService.guardar(paciente);
        URI location = new URI("/dentalflamingos/pacientes/"+paciente1.getIdPaciente());
        return ResponseEntity.created(location).body(paciente1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) {
        if (pacienteService.deletePaciente(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> modificarPaciente(@PathVariable Integer id, @RequestBody Paciente paciente) {
        paciente.setIdPaciente(id);
        Paciente pacienteUpdated = pacienteService.updatePaciente(paciente);
        return ResponseEntity.ok(pacienteUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Paciente> actualizacionParcial(@PathVariable Integer id, @RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBD = pacienteService.getById(id);
        if (pacienteBD.isPresent()) {
            Paciente modificable = pacienteBD.get();

            if (paciente.getIdPaciente() != null) modificable.setIdPaciente(paciente.getIdPaciente());
            if (paciente.getNombre() != null) modificable.setNombre(paciente.getNombre());
            if (paciente.getDireccion() != null) modificable.setDireccion(paciente.getDireccion());
            if (paciente.getEmail() != null) modificable.setEmail(paciente.getEmail());
            if (paciente.getFechaNac() != null) modificable.setFechaNac(paciente.getFechaNac());
            if (paciente.getNumeroCelular() != null) modificable.setNumeroCelular(paciente.getNumeroCelular());

            return ResponseEntity.ok(pacienteService.updatePaciente(modificable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
