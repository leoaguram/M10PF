package com.dental_flamingos.controller.index;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.service.cita.CitaService;
import com.dental_flamingos.service.paciente.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    PacienteService pacienteService;

    @Autowired
    CitaService citaService;

    @GetMapping("/")
    public String summaryInfo(Model model) {

        List<CitaDTO> citasDeHoy = citaService.getCitasHoy(LocalDate.now());

        model.addAttribute("totalpacientes", pacienteService.getTotalPacientes().toString());
        model.addAttribute("citas", citasDeHoy);
        model.addAttribute("totalcitas", citasDeHoy.size());
        return "index";

    }
}
