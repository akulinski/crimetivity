package com.akulinski.crimetivity.pointsaftyservice.core.services;

import com.akulinski.crimetivity.pointsaftyservice.config.FeignConfiguration;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.CrimeEvent;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.FilteringRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="crimefilteringservice", configuration= FeignConfiguration.class)
public interface FilteringClient {

    @PostMapping(path = "api/v1/filter")
    public List<CrimeEvent> filter(@RequestBody FilteringRequest filteringRequest);
}
