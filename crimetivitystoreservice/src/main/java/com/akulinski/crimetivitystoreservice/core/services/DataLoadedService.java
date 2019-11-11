package com.akulinski.crimetivitystoreservice.core.services;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeType;
import com.akulinski.crimetivitystoreservice.core.domain.DataLoadedResponse;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DataLoadedService {

    private final KafkaTemplate<String, DataLoadedResponse> kafkaTemplate;

    private final CrimeEventRepository crimeEventRepository;

    public DataLoadedService(KafkaTemplate<String, DataLoadedResponse> kafkaTemplate, CrimeEventRepository crimeEventRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.crimeEventRepository = crimeEventRepository;
    }

    public void sendCrimeData(String city, String requestId, Set<CrimeType> crimeTypes, Instant from, Instant to) {

        DataLoadedResponse dataLoadedResponse = new DataLoadedResponse();

        List<CrimeEvent> collect = crimeEventRepository.findByPrimaryKey_CityAndPrimaryKey_CrimeTypeInAndPrimaryKey_DateBetween(city, crimeTypes, Date.from(from),Date.from(to));

        dataLoadedResponse.setCity(city);
        dataLoadedResponse.setCrimeEventList(collect);
        dataLoadedResponse.setTimestamp(new Date().toInstant());
        dataLoadedResponse.setRequestId(requestId);

        kafkaTemplate.send("DATA_LOADED_RESPONSE", dataLoadedResponse);
    }

}
