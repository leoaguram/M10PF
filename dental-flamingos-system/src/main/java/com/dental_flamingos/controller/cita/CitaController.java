package com.dental_flamingos.controller.cita;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.exception.PacienteNotFoundException;
import com.dental_flamingos.mapper.CitaMapper;
import com.dental_flamingos.model.Cita;
import com.dental_flamingos.service.cita.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "citas")
public class CitaController {
    @Autowired
    CitaService citaService;

    @Autowired
    CitaMapper citaMapper;

    @GetMapping("/")
    public ResponseEntity<List<CitaDTO>> listaPaciente(){
        return ResponseEntity.status(HttpStatus.OK).body(citaService.buscarCitas());
    }

    @GetMapping("/hoy")
    public ResponseEntity<List<CitaDTO>> listaCitasHoy(@RequestParam("fecha") LocalDate fecha){
        return ResponseEntity.status(HttpStatus.OK).body(citaService.getCitasHoy(fecha));
    }

    @GetMapping("/{id}/dto")
    public ResponseEntity<CitaDTO> listaCitaByIdDto(@PathVariable Integer id){
        Optional<CitaDTO> cita = citaService.getByIdDto(id);
        if (cita.isPresent()) {
            return ResponseEntity.ok(cita.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> listaCitaByIdEnt(@PathVariable Integer id){
        Optional<Cita> cita = citaService.getByIdEnt(id);
        if (cita.isPresent()) {
            return ResponseEntity.ok(cita.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<CitaDTO> guardarCita(@Valid @RequestBody Cita cita) throws URISyntaxException, ParseException, PacienteNotFoundException {
        CitaDTO cita1 = citaService.guardar(cita);
        URI location = new URI("/dentalflamingos/citas/"+cita1.getIdCita());
        return ResponseEntity.created(location).body(cita1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Integer id) {
        if (citaService.deleteCita(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> modificarCita(@PathVariable Integer id, @RequestBody Cita cita) throws ParseException, PacienteNotFoundException {
        cita.setIdCita(id);
        CitaDTO citaDTOUpdated = citaService.updateCita(cita);
        return ResponseEntity.ok(citaDTOUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizacionParcialDto(@PathVariable Integer id, @RequestBody CitaDTO citaDTO) throws ParseException, PacienteNotFoundException {
        Optional<CitaDTO> citaBD = citaService.getByIdDto(id);
        if (citaBD.isPresent()) {
            CitaDTO modificable = citaBD.get();

            if (citaDTO.getIdCita() != null) modificable.setIdCita(citaDTO.getIdCita());
            if (citaDTO.getNombrePaciente() != null) modificable.setNombrePaciente(citaDTO.getNombrePaciente());
            if (citaDTO.getNombreDentista() != null) modificable.setNombreDentista(citaDTO.getNombreDentista());
            if (citaDTO.getStatus() != null) modificable.setStatus(citaDTO.getStatus());
            if (citaDTO.getTratamiento() != null) modificable.setTratamiento(citaDTO.getTratamiento());
            if (citaDTO.getFechaHoraCita() != null) modificable.setFechaHoraCita(citaDTO.getFechaHoraCita());
            if (citaDTO.getObservaciones() != null) modificable.setObservaciones(citaDTO.getObservaciones());
            return ResponseEntity.ok(citaService.updateCita(citaMapper.toEntity(modificable)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
