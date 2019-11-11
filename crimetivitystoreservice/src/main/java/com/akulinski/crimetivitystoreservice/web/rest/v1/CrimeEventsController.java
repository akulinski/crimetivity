package com.akulinski.crimetivitystoreservice.web.rest.v1;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.dto.CrimeEventDTO;
import com.akulinski.crimetivitystoreservice.core.domain.dto.GetCrimesBetweenDatesDTO;
import com.akulinski.crimetivitystoreservice.core.domain.dto.GetEventsByRadiusDTO;
import com.akulinski.crimetivitystoreservice.core.services.CrimeEventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crimes")
public class CrimeEventsController {


    private CrimeEventsService crimeEventsService;

    public CrimeEventsController(CrimeEventsService crimeEventsService) {
        this.crimeEventsService = crimeEventsService;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody CrimeEventDTO crimeEventDTO) {
        return ResponseEntity.ok(crimeEventsService.createCrimeEvent(crimeEventDTO));
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<CrimeEvent>> getCrimesInCity(@PathVariable("cityName") String cityName) {
        return ResponseEntity.ok(crimeEventsService.getAllCrimesInCity(cityName));
    }

    @PostMapping("/radius")
    public ResponseEntity<List<CrimeEvent>> getCrimesInRadius(@RequestBody GetEventsByRadiusDTO getEventsByRadiusDTO) {
        return ResponseEntity.ok(crimeEventsService.getAllCrimesInRadius(getEventsByRadiusDTO));
    }

    @PostMapping("/date")
    public ResponseEntity<List<CrimeEvent>> getCrimesBetweenDates(@RequestBody GetCrimesBetweenDatesDTO crimesBetweenDatesDTO) {
        return ResponseEntity.ok(crimeEventsService.getCrimesBetweenDates(crimesBetweenDatesDTO));
    }

}
