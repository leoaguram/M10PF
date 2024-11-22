package com.dental_flamingos.clienteweb.service.tratamiento;

import com.dental_flamingos.model.Tratamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TratamientoWebClientService {
    @Autowired
    WebClient webClient;

    public List<Tratamiento> getAll() {
        Mono<List<Tratamiento>> listMono = webClient.get()
                .uri("/tratamientos/")
                .retrieve()
                .bodyToFlux(Tratamiento.class)
                .collectList();
        List<Tratamiento> tratamientos = listMono.block();
        return tratamientos;
    }

    public Tratamiento getTratamientoById(Integer id) {
        Mono<Tratamiento> tratamientoMono = webClient.get()
                .uri("/tratamientos/{id}", id)
                .retrieve()
                .bodyToMono(Tratamiento.class);
        Tratamiento tratamiento = tratamientoMono.block();
        return tratamiento;
    }

    public Tratamiento actualizaTratamiento(Tratamiento tratamiento) {
        return webClient.put()
                .uri("/tratamientos/{id}", tratamiento.getIdTratamiento())
                .bodyValue(tratamiento)
                .retrieve()
                .bodyToMono(Tratamiento.class)
                .block();
    }

    public Tratamiento guardaTratamiento(Tratamiento tratamiento) {
        return webClient.post()
                .uri("/tratamientos/")
                .bodyValue(tratamiento)
                .retrieve()
                .bodyToMono(Tratamiento.class)
                .block();
    }

    public void eliminaTratamiento(Integer idTratamiento) {
        webClient.delete()
                .uri("/tratamientos/{id}", idTratamiento)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new RuntimeException("Error 4xx: El tratamiento no pudo ser eliminado."));
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    return Mono.error(new RuntimeException("Error 5xx: Problemas con el servidor al intentar borrar el tratamiento."));
                })
                .toBodilessEntity()
                .block();
    }


}
