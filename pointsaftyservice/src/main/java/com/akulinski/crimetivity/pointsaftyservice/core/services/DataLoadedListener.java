package com.akulinski.crimetivity.pointsaftyservice.core.services;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.DataLoadedResponse;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.LoadDataRequest;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.RequestStatus;
import com.akulinski.crimetivity.pointsaftyservice.core.repository.LoadDataRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DataLoadedListener {

    private final LoadDataRequestRepository loadDataRequestRepository;

    public DataLoadedListener(LoadDataRequestRepository loadDataRequestRepository) {
        this.loadDataRequestRepository = loadDataRequestRepository;
    }

    @KafkaListener(topics = "DATA_LOADED_RESPONSE", groupId = "crtimestoreservice")
    public void handleLoadedData(DataLoadedResponse dataLoadedResponse) {
        Optional<LoadDataRequest> byId = loadDataRequestRepository.findById(dataLoadedResponse.getRequestId());
        LoadDataRequest loadDataRequest = byId.orElseThrow(() ->
                new IllegalArgumentException(String.format("No request found with id %s", dataLoadedResponse.getRequestId())));
        loadDataRequest.setRequestStatus(RequestStatus.CREATED);
        loadDataRequestRepository.save(loadDataRequest);
    }
}
