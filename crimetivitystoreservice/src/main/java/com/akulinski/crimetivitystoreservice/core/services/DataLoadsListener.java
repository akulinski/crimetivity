package com.akulinski.crimetivitystoreservice.core.services;

import com.akulinski.crimetivitystoreservice.core.domain.LoadDataRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DataLoadsListener {

    private final DataLoadedService dataLoadedService;

    public DataLoadsListener(DataLoadedService dataLoadedService) {
        this.dataLoadedService = dataLoadedService;
    }

    @KafkaListener(topics = "LOAD_DATA_REQUESTS", groupId = "pointsaftyservice", containerFactory = "containerFactory")
    public void listen(LoadDataRequest loadDataRequest) {
        dataLoadedService.sendCrimeData(loadDataRequest.getCityName(), loadDataRequest.getId());
    }
}
