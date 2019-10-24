package com.akulinski.crimetivitystoreservice.core.services;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.dto.CrimeEventDTO;
import com.akulinski.crimetivitystoreservice.core.domain.dto.GetEventsByRadiusDTO;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class CrimeEventsService {

    private CrimeEventRepository crimeEventRepository;

    public CrimeEventsService(CrimeEventRepository crimeEventRepository) {
        this.crimeEventRepository = crimeEventRepository;
    }

    public Mono<CrimeEvent> createCrimeEvent(CrimeEventDTO crimeEventDTO) {
        CrimeEvent crimeEvent = new CrimeEvent();
        crimeEvent.setCity(crimeEventDTO.getCity().toLowerCase());
        crimeEvent.setCrimeType(crimeEventDTO.getCrimeType());
        crimeEvent.setLatitude(crimeEvent.getLatitude());
        crimeEvent.setLongitude(crimeEvent.getLongitude());

        return crimeEventRepository.save(crimeEvent);
    }

    public Flux<CrimeEvent> getAllCrimesInCity(String city) {
        return crimeEventRepository.findByCity(city.toLowerCase());
    }

    public Flux<CrimeEvent> getAllCrimesInRadius(GetEventsByRadiusDTO getEventsByRadiusDTO) {

        return crimeEventRepository.findByCity(getEventsByRadiusDTO.getCity().toLowerCase()).filter(data -> {
            double distance = getDistance(getEventsByRadiusDTO, data);
            return distance <= getEventsByRadiusDTO.getRadius();
        });
    }

    private double getDistance(GetEventsByRadiusDTO getEventsByRadiusDTO, CrimeEvent data) {
        BigDecimal latitude = data.getLatitude();
        BigDecimal longitude = data.getLongitude();
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        return euclideanDistance.compute(new double[]{latitude.doubleValue(), longitude.doubleValue()},
                new double[]{getEventsByRadiusDTO.getLatitude().doubleValue(), getEventsByRadiusDTO.getLongitude().doubleValue()});
    }
}
