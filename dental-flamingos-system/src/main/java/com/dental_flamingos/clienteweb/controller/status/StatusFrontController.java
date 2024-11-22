package com.dental_flamingos.clienteweb.controller.status;

import com.dental_flamingos.clienteweb.service.status.StatusWebClientService;
import com.dental_flamingos.model.CatalogoStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/front/status")
public class StatusFrontController {
    @Autowired
    StatusWebClientService statusWebClientService;

    @GetMapping("/lista-status")
    public String listaStatus(Model model){

        model.addAttribute("status", statusWebClientService.getAll());
        model.addAttribute("contenido","Lista de Status");
        return "status/lista-status";
    }

    @GetMapping("/{id}/editar")
    public String getFormEditar(@PathVariable Integer id, Model model) {
        CatalogoStatus status = statusWebClientService.getStatusById(id);
        model.addAttribute("contenido", "Modificación de un Status");
        model.addAttribute("status", status);
        return "status/alta-status";
    }

    @GetMapping("/alta")
    public String altaStatus(Model model) {
        CatalogoStatus status = new CatalogoStatus();
        model.addAttribute("contenido", "Alta de un nuevo Status");
        model.addAttribute("status", status);
        return "status/alta-status";
    }

    @PostMapping("/guardar")
    public String guardarStatus(@Valid @ModelAttribute("status") CatalogoStatus status, BindingResult bindingResult,
                                     Model model, RedirectAttributes flash) {
        if(bindingResult.hasErrors()){
            model.addAttribute("contenido", "Alta de un nuevo Status");
            return "status/alta-status";
        }
        if (status.getIdStatus() != null) {
            statusWebClientService.actualizaStatus(status);
        } else
            statusWebClientService.guardarStatus(status);
        flash.addFlashAttribute("success", "Se almacenó con éxito");
        return "redirect:/front/status/lista-status";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarStatus(@PathVariable Integer id, Model model, RedirectAttributes flash) {
        try {
            statusWebClientService.eliminaStatus(id);
            flash.addFlashAttribute("success", "Tratamiento eliminado exitosamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el tratamiento: " + e.getMessage());
        }
        return "redirect:/front/status/lista-status";
    }

}
