package com.akulinski.crimetivity.pointsaftyservice.config;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.CrimeEvent;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.FilteringRequest;
import com.akulinski.crimetivity.pointsaftyservice.core.services.FilteringClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterFallback implements FilteringClient {
    @Override
    public List<CrimeEvent> filter(FilteringRequest filteringRequest) {
        return filteringRequest.getCrimeEvents();
    }
}
