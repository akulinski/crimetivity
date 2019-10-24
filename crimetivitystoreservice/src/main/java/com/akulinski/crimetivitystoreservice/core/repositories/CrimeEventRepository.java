package com.akulinski.crimetivitystoreservice.core.repositories;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CrimeEventRepository extends ReactiveMongoRepository<CrimeEvent, String> {
    Flux<CrimeEvent> findByCity(String city);
}
