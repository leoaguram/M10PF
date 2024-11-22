package com.dental_flamingos.clienteweb.service.paciente;

import com.dental_flamingos.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PacienteWebClientService {
    @Autowired
    WebClient webClient;

    public List<Paciente> getAll() {
        Mono<List<Paciente>> listMono = webClient.get()
                .uri("/pacientes/")
                .retrieve()
                .bodyToFlux(Paciente.class)
                .collectList();
        List<Paciente> pacientes = listMono.block();
        return pacientes;
    }

    public Long getTotalPacientes() {
        return webClient.get()
                .uri("/pacientes/total")
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    public Paciente getPacienteById(Integer id) {
        Mono<Paciente> statusMono = webClient.get()
                .uri("/pacientes/{id}", id)
                .retrieve()
                .bodyToMono(Paciente.class);
        Paciente paciente = statusMono.block();
        return paciente;
    }

    public Paciente actualizaPaciente(Paciente paciente) {
        return webClient.put()
                .uri("/pacientes/{id}", paciente.getIdPaciente())
                .bodyValue(paciente)
                .retrieve()
                .bodyToMono(Paciente.class)
                .block();
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return webClient.post()
                .uri("/pacientes/{id}", paciente.getIdPaciente())
                .bodyValue(paciente)
                .retrieve()
                .bodyToMono(Paciente.class)
                .block();
    }

    public void eliminaPaciente(Integer idPaciente) {
        webClient.delete()
                .uri("/pacientes/{id}", idPaciente)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new RuntimeException("Error 4xx: El paciente no pudo ser eliminado."));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    return Mono.error(new RuntimeException("Error 5xx: Problemas con el servidor al intentar borrar el paciente."));
                })
                .toBodilessEntity()
                .block();
    }
}
