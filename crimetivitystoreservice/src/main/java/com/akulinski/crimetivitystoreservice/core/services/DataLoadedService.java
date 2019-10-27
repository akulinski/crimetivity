package com.akulinski.crimetivitystoreservice.core.services;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.DataLoadedResponse;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class DataLoadedService {

    private final KafkaTemplate<String, DataLoadedResponse> kafkaTemplate;

    private final CrimeEventRepository crimeEventRepository;

    public DataLoadedService(KafkaTemplate<String, DataLoadedResponse> kafkaTemplate, CrimeEventRepository crimeEventRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.crimeEventRepository = crimeEventRepository;
    }

    public void sendCrimeData(String city, String requestId) {

        DataLoadedResponse dataLoadedResponse = new DataLoadedResponse();

        List<CrimeEvent> crimeEventList = new LinkedList<>();

        crimeEventRepository.findByCity(city).toStream().forEach(crimeEventList::add);

        dataLoadedResponse.setCity(city);
        dataLoadedResponse.setCrimeEventList(crimeEventList);
        dataLoadedResponse.setTimestamp(new Date().toInstant());
        dataLoadedResponse.setRequestId(requestId);

        kafkaTemplate.send("DATA_LOADED_RESPONSE", dataLoadedResponse);
    }

}
