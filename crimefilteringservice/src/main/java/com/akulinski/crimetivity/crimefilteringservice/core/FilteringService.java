package com.akulinski.crimetivity.crimefilteringservice.core;

import com.akulinski.crimetivity.crimefilteringservice.core.domain.CrimeEvent;
import com.akulinski.crimetivity.crimefilteringservice.core.domain.FilteringRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilteringService {

    public List<CrimeEvent> filterEvents(FilteringRequest filteringRequest){

        return filteringRequest.getCrimeEvents().stream()
                .filter(crimeEvent -> crimeEvent.getDate().before(filteringRequest.getEnd()) && crimeEvent.getDate().after(filteringRequest.getEnd()))
                .collect(Collectors.toList());
    }
}
