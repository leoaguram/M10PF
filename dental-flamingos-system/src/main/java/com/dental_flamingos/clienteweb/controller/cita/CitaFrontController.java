package com.dental_flamingos.clienteweb.controller.cita;

import com.dental_flamingos.clienteweb.service.cita.CitaWebClientService;
import com.dental_flamingos.clienteweb.service.dentista.DentistaWebClientService;
import com.dental_flamingos.clienteweb.service.paciente.PacienteWebClientService;
import com.dental_flamingos.clienteweb.service.status.StatusWebClientService;
import com.dental_flamingos.clienteweb.service.tratamiento.TratamientoWebClientService;
import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/front/citas")
public class CitaFrontController {
    @Autowired
    CitaWebClientService citaWebClientService;

   @Autowired
    DentistaWebClientService dentistaWebClientService;

   @Autowired
    PacienteWebClientService pacienteWebClientService;

   @Autowired
    TratamientoWebClientService tratamientoWebClientService;

   @Autowired
    StatusWebClientService statusWebClientService;

    @GetMapping("/lista-cita")
    public String listaCitas(Model model){

        model.addAttribute("cita", citaWebClientService.getAll());
        model.addAttribute("contenido","Lista de Citas");
        return "cita/lista-cita";
    }

    @GetMapping("/{id}/editar")
    public String getFormEditar(@PathVariable Integer id, Model model) {
        Cita cita = citaWebClientService.getCitaById(id);
        model.addAttribute("contenido", "Modificación de una Cita");
        model.addAttribute("cita", cita);

        List<CatalogoStatus> selectStatus = statusWebClientService.getAll();

        model.addAttribute("selectPaciente", cita.getPaciente());
        model.addAttribute("selectDentista", cita.getDentista());
        model.addAttribute("selectTratamiento", cita.getTratamiento());
        model.addAttribute("selectStatus", selectStatus);
        return "cita/alta-cita";
    }

    @GetMapping("alta-cita")
    public String altaCita(Model model){
        Cita cita = new Cita();
        List<Paciente> selectPaciente = pacienteWebClientService.getAll();
        List<Dentista> selectDentista = dentistaWebClientService.getAll();
        List<Tratamiento> selectTratamiento = tratamientoWebClientService.getAll();
        List<CatalogoStatus> selectStatus = statusWebClientService.getAll();

        model.addAttribute("contenido", "Alta de Cita");
        model.addAttribute("cita", cita);
        model.addAttribute("selectPaciente", selectPaciente);
        model.addAttribute("selectDentista", selectDentista);
        model.addAttribute("selectTratamiento", selectTratamiento);
        model.addAttribute("selectStatus", selectStatus);
        return "cita/alta-cita";
    }

    @PostMapping("/guardar")
    public String guardarCita(@Valid @ModelAttribute("cita") Cita cita, BindingResult bindingResult,
                                  Model model, RedirectAttributes flash) {

        if(bindingResult.hasErrors()){
            model.addAttribute("contenido", "Alta de una nueva Cita");
            return "cita/alta-cita";
        }
        if (cita.getIdCita() != null) {
            citaWebClientService.actualizaCita(cita);
        } else
            citaWebClientService.guardarCita(cita);
        flash.addFlashAttribute("success", "Se almacenó con éxito");
        return "redirect:/front/citas/lista-cita";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Integer id, Model model, RedirectAttributes flash) {
        try {
            citaWebClientService.eliminaCita(id);
            flash.addFlashAttribute("success", "Cita eliminada exitosamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar la cita: " + e.getMessage());
        }
        return "redirect:/front/citas/lista-cita";
    }
}
