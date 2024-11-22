package com.dental_flamingos.clienteweb.service.dentista;

import com.dental_flamingos.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DentistaWebClientService {
    @Autowired
    WebClient webClient;

    public List<Dentista> getAll() {
        Mono<List<Dentista>> listMono = webClient.get()
                .uri("/dentistas/")
                .retrieve()
                .bodyToFlux(Dentista.class)
                .collectList();
        List<Dentista> dentistaList = listMono.block();
        return dentistaList;
    }

    public Dentista getDentistaById(Integer id) {
        Mono<Dentista> dentistaMono = webClient.get()
                .uri("/dentistas/{id}", id)
                .retrieve()
                .bodyToMono(Dentista.class);
        Dentista dentista = dentistaMono.block();
        return dentista;
    }

    public Dentista actualizaDentista(Dentista dentista) {
        return webClient.put()
                .uri("/dentistas/{id}", dentista.getIdDentista())
                .bodyValue(dentista)
                .retrieve()
                .bodyToMono(Dentista.class)
                .block();
    }
}
