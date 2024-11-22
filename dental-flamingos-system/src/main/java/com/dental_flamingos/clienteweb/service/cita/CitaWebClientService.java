package com.dental_flamingos.clienteweb.service.cita;

import com.dental_flamingos.dto.CitaDTO;
import com.dental_flamingos.model.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CitaWebClientService {
    @Autowired
    WebClient webClient;

    public List<CitaDTO> getAll() {
        Mono<List<CitaDTO>> listMono = webClient.get()
                .uri("/citas/")
                .retrieve()
                .bodyToFlux(CitaDTO.class)
                .collectList();
        List<CitaDTO> citas = listMono.block();
        return citas;
    }

    public List<Cita> getCitasHoy(LocalDate fecha) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/citas/hoy").queryParam("fecha", fecha.toString()).build())
                .retrieve()
                .bodyToFlux(Cita.class)
                .collectList()
                .block();
    }

    public Cita getCitaById(Integer id) {
        Mono<Cita> statusMono = webClient.get()
                .uri("/citas/{id}", id)
                .retrieve()
                .bodyToMono(Cita.class);
        Cita cita = statusMono.block();
        return cita;
    }

    public CitaDTO actualizaCita(Cita cita) {
        return webClient.put()
                .uri("/citas/{id}", cita.getIdCita())
                .bodyValue(cita)
                .retrieve()
                .bodyToMono(CitaDTO.class)
                .block();
    }

    public CitaDTO guardarCita(Cita cita) {
        return webClient.post()
                .uri("/citas/")
                .bodyValue(cita)
                .retrieve()
                .bodyToMono(CitaDTO.class)
                .block();
    }

    public void eliminaCita(Integer idCita) {
        webClient.delete()
                .uri("/citas/{id}", idCita)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new RuntimeException("Error 4xx: La cita no pudo ser eliminado."));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    return Mono.error(new RuntimeException("Error 5xx: Problemas con el servidor al intentar borrar la cita."));
                })
                .toBodilessEntity()
                .block();
    }
}
