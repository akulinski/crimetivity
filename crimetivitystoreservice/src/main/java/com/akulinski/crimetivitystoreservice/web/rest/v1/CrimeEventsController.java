package com.akulinski.crimetivitystoreservice.web.rest.v1;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.dto.CrimeEventDTO;
import com.akulinski.crimetivitystoreservice.core.domain.dto.GetEventsByRadiusDTO;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import com.akulinski.crimetivitystoreservice.core.services.CrimeEventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/crimes")
public class CrimeEventsController {

    private CrimeEventRepository crimeEventRepository;

    private CrimeEventsService crimeEventsService;

    public CrimeEventsController(CrimeEventRepository crimeEventRepository, CrimeEventsService crimeEventsService) {
        this.crimeEventRepository = crimeEventRepository;
        this.crimeEventsService = crimeEventsService;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody CrimeEventDTO crimeEventDTO) {
        return ResponseEntity.ok(crimeEventsService.createCrimeEvent(crimeEventDTO));
    }

    @GetMapping("/city/{cityName}")
    public Flux<CrimeEvent> getCrimesInCity(@PathVariable("cityName") String cityName) {
        return crimeEventsService.getAllCrimesInCity(cityName);
    }

    @PostMapping("/radius")
    public Flux<CrimeEvent> getCrimesInRadius(@RequestBody GetEventsByRadiusDTO getEventsByRadiusDTO) {
        return crimeEventsService.getAllCrimesInRadius(getEventsByRadiusDTO);
    }

    @GetMapping
    public Flux<CrimeEvent> findAll() {
        return crimeEventRepository.findAll();
    }
}
