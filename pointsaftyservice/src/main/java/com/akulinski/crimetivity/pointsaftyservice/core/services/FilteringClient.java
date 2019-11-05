package com.akulinski.crimetivity.pointsaftyservice.core.services;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.CrimeEvent;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.FilteringRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Service
public class FilteringClient {
    private final WebClient webClient;

    public FilteringClient(){
        webClient = WebClient.builder().baseUrl("http://crimefilteringservice:8080/api/v1/filter").build();
    }

    @PostMapping(path = "/api/v1/filter")
    Flux<CrimeEvent> filter(@RequestBody FilteringRequest filteringRequest){
        return  webClient.post().body(BodyInserters.fromObject(filteringRequest)).retrieve().bodyToFlux(CrimeEvent.class);
    }
}
