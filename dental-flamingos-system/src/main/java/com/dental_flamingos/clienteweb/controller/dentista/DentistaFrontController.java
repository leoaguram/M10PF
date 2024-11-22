package com.dental_flamingos.clienteweb.controller.dentista;

import com.dental_flamingos.clienteweb.service.dentista.DentistaWebClientService;
import com.dental_flamingos.model.Dentista;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/front/dentistas")
public class DentistaFrontController {
    @Autowired
    DentistaWebClientService dentistaWebClientService;

    @GetMapping("/lista-dentista")
    public String listaStatus(Model model){

        model.addAttribute("dentista", dentistaWebClientService.getAll());
        model.addAttribute("contenido","Lista de Dentistas");
        return "dentista/lista-dentista";
    }

    @GetMapping("/{id}/editar")
    public String getFormEditar(@PathVariable Integer id, Model model) {
        Dentista dentista = dentistaWebClientService.getDentistaById(id);
        model.addAttribute("contenido", "Modificación de un Dentista");
        model.addAttribute("dentista", dentista);
        return "dentista/edita-dentista";
    }

    @PostMapping("/guardar")
    public String guardarStatus(@Valid @ModelAttribute("dentista") Dentista dentista, BindingResult bindingResult,
                                Model model, RedirectAttributes flash) {
        if(bindingResult.hasErrors()){
            model.addAttribute("contenido", "Modificación de un Dentista");
            return "dentista/modifica-dentista";
        }
        dentistaWebClientService.actualizaDentista(dentista);
        flash.addFlashAttribute("success", "Se almacenó con éxito");
        return "redirect:/front/dentistas/lista-dentista";
    }

}
