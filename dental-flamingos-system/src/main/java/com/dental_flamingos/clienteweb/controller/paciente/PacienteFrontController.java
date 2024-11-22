package com.dental_flamingos.clienteweb.controller.paciente;

import com.dental_flamingos.clienteweb.service.paciente.PacienteWebClientService;
import com.dental_flamingos.model.Paciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/front/pacientes")
public class PacienteFrontController {
    @Autowired
    PacienteWebClientService pacienteWebClientService;

    @GetMapping("/lista-paciente")
    public String listaPacientes(Model model){

        model.addAttribute("paciente", pacienteWebClientService.getAll());
        model.addAttribute("contenido","Lista de Pacientes");
        return "paciente/lista-paciente";
    }

    @GetMapping("/{id}/editar")
    public String getFormEditar(@PathVariable Integer id, Model model) {
        Paciente paciente = pacienteWebClientService.getPacienteById(id);
        model.addAttribute("contenido", "Modificación de un Paciente");
        model.addAttribute("paciente", paciente);
        return "paciente/alta-paciente";
    }

    @GetMapping("/alta")
    public String altaPaciente(Model model) {
        Paciente paciente = new Paciente();
        model.addAttribute("contenido", "Alta de un nuevo Paciente");
        model.addAttribute("paciente", paciente);
        return "paciente/alta-paciente";
    }

    @PostMapping("/guardar")
    public String guardarPaciente(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult bindingResult,
                                Model model, RedirectAttributes flash) {
        if(bindingResult.hasErrors()){
            model.addAttribute("contenido", "Alta de un nuevo Paciente");
            return "paciente/alta-paciente";
        }
        if (paciente.getIdPaciente() != null) {
            pacienteWebClientService.actualizaPaciente(paciente);
        } else
            pacienteWebClientService.guardarPaciente(paciente);
        flash.addFlashAttribute("success", "Se almacenó con éxito");
        return "redirect:/front/pacientes/lista-paciente";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Integer id, Model model, RedirectAttributes flash) {
        try {
            pacienteWebClientService.eliminaPaciente(id);
            flash.addFlashAttribute("success", "Paciente eliminado exitosamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el paciente: " + e.getMessage());
        }
        return "redirect:/front/pacientes/lista-paciente";
    }
}
