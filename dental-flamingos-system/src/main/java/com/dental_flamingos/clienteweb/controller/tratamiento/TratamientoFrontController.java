package com.dental_flamingos.clienteweb.controller.tratamiento;

import com.dental_flamingos.clienteweb.service.tratamiento.TratamientoWebClientService;
import com.dental_flamingos.model.Tratamiento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/front/tratamientos")
public class TratamientoFrontController {

    @Autowired
    TratamientoWebClientService tratamientoWebClientService;

    @GetMapping("/lista-tratamiento")
    public String listaTratamientos(Model model){

        model.addAttribute("tratamiento", tratamientoWebClientService.getAll());
        model.addAttribute("contenido","Lista de Tratamientos");
        return "tratamiento/lista-tratamiento";
    }

    @GetMapping("/{id}/editar")
    public String getFormEditar(@PathVariable Integer id, Model model) {
        Tratamiento tratamiento = tratamientoWebClientService.getTratamientoById(id);
        model.addAttribute("contenido", "Editar Tratamiento");
        model.addAttribute("tratamiento", tratamiento);
        return "tratamiento/alta-tratamiento";
    }

    @GetMapping("/alta")
    public String nuevoTrat(Model model) {
        Tratamiento tratamiento = new Tratamiento();
        model.addAttribute("contenido", "Alta de un nuevo Tratamiento");
        model.addAttribute("tratamiento", tratamiento);
        return "tratamiento/alta-tratamiento";
    }

    @PostMapping("/guardar")
    public String guardarTratamiento(@Valid @ModelAttribute("tratamiento") Tratamiento tratamiento, BindingResult bindingResult,
                                     Model model, RedirectAttributes flash) {
        if(bindingResult.hasErrors()){
            model.addAttribute("contenido", "Alta de un nuevo Tratamiento");
            return "tratamiento/alta-tratamiento";
        }
        System.out.println("Formulario: " + tratamiento);
        if (tratamiento.getIdTratamiento() != null) {
            tratamientoWebClientService.actualizaTratamiento(tratamiento);
        }
        else
            tratamientoWebClientService.guardaTratamiento(tratamiento);

        flash.addFlashAttribute("success", "Se almacenó con éxito");
        return "redirect:/front/tratamientos/lista-tratamiento";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTratamiento(@PathVariable Integer id, Model model, RedirectAttributes flash) {
        try {
            tratamientoWebClientService.eliminaTratamiento(id);
            model.addAttribute("mensaje", "Tratamiento eliminado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar el tratamiento: " + e.getMessage());
        }
        return "redirect:/front/tratamientos/lista-tratamiento";
    }

}
