package com.akulinski.crimetivity.pointsaftyservice.core.services;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.*;
import com.akulinski.crimetivity.pointsaftyservice.core.repository.LoadDataRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DataLoadedListener {

    private final FilteringClient filteringClient;

    private final LoadDataRequestRepository loadDataRequestRepository;

    public DataLoadedListener(LoadDataRequestRepository loadDataRequestRepository, FilteringClient filteringClient) {
        this.loadDataRequestRepository = loadDataRequestRepository;
        this.filteringClient = filteringClient;
    }

    @KafkaListener(topics = "DATA_LOADED_RESPONSE", groupId = "crtimestoreservice")
    public void handleLoadedData(DataLoadedResponse dataLoadedResponse) {

        Optional<LoadDataRequest> byId = loadDataRequestRepository.findById(dataLoadedResponse.getRequestId());

        LoadDataRequest loadDataRequest = byId.orElseThrow(() ->
                new IllegalArgumentException(String.format("No request found with id %s", dataLoadedResponse.getRequestId())));

        FilteringRequest filteringRequest = new FilteringRequest();
        filteringRequest.setCrimeEvents(dataLoadedResponse.getCrimeEventList());
        filteringRequest.setId(loadDataRequest.getId());
        filteringRequest.setLat(loadDataRequest.getLat());
        filteringRequest.setLon(loadDataRequest.getLon());

        List<CrimeEvent> filter = filteringClient.filter(filteringRequest);

        if (filter.size() > 0) {
            loadDataRequest.setSafetyStatus(SafetyStatus.NOT_SAFE);
        } else {
            loadDataRequest.setSafetyStatus(SafetyStatus.SAFE);
        }

        loadDataRequest.setRequestStatus(RequestStatus.CREATED);
        loadDataRequestRepository.save(loadDataRequest);
    }
}
