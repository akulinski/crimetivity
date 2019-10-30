package com.akulinski.crimetivity.crimefilteringservice.core;

import com.akulinski.crimetivity.crimefilteringservice.core.domain.CrimeEvent;
import com.akulinski.crimetivity.crimefilteringservice.core.domain.FilteringRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filter")
public class FilteringController {

    private final FilteringService filteringService;

    public FilteringController(FilteringService filteringService) {
        this.filteringService = filteringService;
    }

    @PostMapping
    public ResponseEntity<List<CrimeEvent>> filterEvents(@RequestBody FilteringRequest filteringRequest){
        return ResponseEntity.ok(filteringService.filterEvents(filteringRequest));
    }
}
