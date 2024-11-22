package com.dental_flamingos.clienteweb.controller.index;

import com.dental_flamingos.clienteweb.service.cita.CitaWebClientService;
import com.dental_flamingos.clienteweb.service.paciente.PacienteWebClientService;
import com.dental_flamingos.model.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/front")
public class IndexFrontController {
    @Autowired
    PacienteWebClientService pacienteWebClientService;

    @Autowired
    CitaWebClientService citaWebClientService;

    @GetMapping("/")
    public String summaryInfo(Model model) {

        List<Cita> citasDeHoy = citaWebClientService.getCitasHoy(LocalDate.now());

        model.addAttribute("totalpacientes", pacienteWebClientService.getTotalPacientes().toString());
        model.addAttribute("citas", citasDeHoy);
        model.addAttribute("totalcitas", citasDeHoy.size());
        return "index";
    }
}
