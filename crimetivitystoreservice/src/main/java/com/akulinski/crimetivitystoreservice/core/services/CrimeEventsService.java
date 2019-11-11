package com.akulinski.crimetivitystoreservice.core.services;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeEventPrimaryKey;
import com.akulinski.crimetivitystoreservice.core.domain.dto.CrimeEventDTO;
import com.akulinski.crimetivitystoreservice.core.domain.dto.GetCrimesBetweenDatesDTO;
import com.akulinski.crimetivitystoreservice.core.domain.dto.GetEventsByRadiusDTO;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrimeEventsService {

    private CrimeEventRepository crimeEventRepository;

    public CrimeEventsService(CrimeEventRepository crimeEventRepository) {
        this.crimeEventRepository = crimeEventRepository;
    }

    public CrimeEvent createCrimeEvent(CrimeEventDTO crimeEventDTO) {
        CrimeEvent crimeEvent = new CrimeEvent();
        CrimeEventPrimaryKey crimeEventPrimaryKey = new CrimeEventPrimaryKey(crimeEventDTO.getCity(), crimeEventDTO.getCrimeType(), new java.util.Date());
        crimeEvent.setPrimaryKey(crimeEventPrimaryKey);
        crimeEvent.setLatitude(crimeEvent.getLatitude());
        crimeEvent.setLongitude(crimeEvent.getLongitude());

        return crimeEventRepository.save(crimeEvent);
    }

    public List<CrimeEvent> getAllCrimesInCity(String city) {
        return crimeEventRepository.findByPrimaryKey_City(city.toLowerCase());
    }

    public List<CrimeEvent> getCrimesBetweenDates(GetCrimesBetweenDatesDTO getCrimesBetweenDatesDTO) {
        return crimeEventRepository.findByPrimaryKey_CityAndPrimaryKey_CrimeTypeAndPrimaryKey_DateBetween(getCrimesBetweenDatesDTO.getCity(), getCrimesBetweenDatesDTO.getCrimeType(), Date.from(getCrimesBetweenDatesDTO.getFrom()), Date.from(getCrimesBetweenDatesDTO.getTo()));
    }

    public List<CrimeEvent> getAllCrimesInRadius(GetEventsByRadiusDTO getEventsByRadiusDTO) {

        return crimeEventRepository.findByPrimaryKey_City(getEventsByRadiusDTO.getCity().toLowerCase()).stream().filter(data -> {
            double distance = getDistance(getEventsByRadiusDTO, data);
            return distance <= getEventsByRadiusDTO.getRadius();
        }).collect(Collectors.toList());
    }

    private double getDistance(GetEventsByRadiusDTO getEventsByRadiusDTO, CrimeEvent data) {
        BigDecimal latitude = data.getLatitude();
        BigDecimal longitude = data.getLongitude();
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        return euclideanDistance.compute(new double[]{latitude.doubleValue(), longitude.doubleValue()},
                new double[]{getEventsByRadiusDTO.getLatitude().doubleValue(), getEventsByRadiusDTO.getLongitude().doubleValue()});
    }
}
