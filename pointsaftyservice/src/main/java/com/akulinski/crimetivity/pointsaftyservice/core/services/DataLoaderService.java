package com.akulinski.crimetivity.pointsaftyservice.core.services;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.LoadDataRequest;
import com.akulinski.crimetivity.pointsaftyservice.core.domain.RequestStatus;
import com.akulinski.crimetivity.pointsaftyservice.core.repository.LoadDataRequestRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataLoaderService {

    private final LoadDataRequestRepository loadDataRequestRepository;

    private final KafkaTemplate<String, LoadDataRequest> kafkaTemplate;

    public DataLoaderService(KafkaTemplate<String, LoadDataRequest> kafkaTemplate, LoadDataRequestRepository loadDataRequestRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.loadDataRequestRepository = loadDataRequestRepository;
    }

    public String sendDataRequest(String city, String lat, String lon){
        LoadDataRequest loadDataRequest = new LoadDataRequest();
        loadDataRequest.setCityName(city);
        loadDataRequest.setLat(lat);
        loadDataRequest.setLon(lon);
        loadDataRequest.setRequestStatus(RequestStatus.CREATED);

        LoadDataRequest save = loadDataRequestRepository.save(loadDataRequest);

        kafkaTemplate.send("LOAD_DATA_REQUESTS", save);

        save.setRequestStatus(RequestStatus.IN_PROGRESS);

        loadDataRequestRepository.save(save);

        return save.getId();
    }
}
