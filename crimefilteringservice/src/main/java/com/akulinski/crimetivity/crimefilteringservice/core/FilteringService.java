package com.akulinski.crimetivity.crimefilteringservice.core;

import com.akulinski.crimetivity.crimefilteringservice.core.domain.CrimeEvent;
import com.akulinski.crimetivity.crimefilteringservice.core.domain.FilteringRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilteringService {

    public List<CrimeEvent> filterEvents(FilteringRequest filteringRequest){
        return filteringRequest.getCrimeEvents().stream().skip(3).collect(Collectors.toList());
    }
}
