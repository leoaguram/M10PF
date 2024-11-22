package com.dental_flamingos.clienteweb.service.status;

import com.dental_flamingos.model.CatalogoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StatusWebClientService {
    @Autowired
    WebClient webClient;

    public List<CatalogoStatus> getAll() {
        Mono<List<CatalogoStatus>> listMono = webClient.get()
                .uri("/status/")
                .retrieve()
                .bodyToFlux(CatalogoStatus.class)
                .collectList();
        List<CatalogoStatus> statusList = listMono.block();
        return statusList;
    }

    public CatalogoStatus getStatusById(Integer id) {
        Mono<CatalogoStatus> statusMono = webClient.get()
                .uri("/status/{id}", id)
                .retrieve()
                .bodyToMono(CatalogoStatus.class);
        CatalogoStatus status = statusMono.block();
        return status;
    }

    public CatalogoStatus actualizaStatus(CatalogoStatus status) {
        return webClient.put()
                .uri("/status/{id}", status.getIdStatus())
                .bodyValue(status)
                .retrieve()
                .bodyToMono(CatalogoStatus.class)
                .block();
    }

    public CatalogoStatus guardarStatus(CatalogoStatus status) {
        return webClient.post()
                .uri("/status/{id}", status.getIdStatus())
                .bodyValue(status)
                .retrieve()
                .bodyToMono(CatalogoStatus.class)
                .block();
    }

    public void eliminaStatus(Integer idStatus) {
        webClient.delete()
                .uri("/status/{id}", idStatus)
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
