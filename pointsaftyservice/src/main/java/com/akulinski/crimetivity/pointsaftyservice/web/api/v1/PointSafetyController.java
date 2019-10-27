package com.akulinski.crimetivity.pointsaftyservice.web.api.v1;

import com.akulinski.crimetivity.pointsaftyservice.core.repository.LoadDataRequestRepository;
import com.akulinski.crimetivity.pointsaftyservice.core.services.DataLoaderService;
import com.akulinski.crimetivity.pointsaftyservice.web.CheckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/safety")
public class PointSafetyController {

    private final LoadDataRequestRepository loadDataRequestRepository;

    private final DataLoaderService loaderService;

    public PointSafetyController(DataLoaderService loaderService, LoadDataRequestRepository loadDataRequestRepository) {
        this.loaderService = loaderService;
        this.loadDataRequestRepository = loadDataRequestRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(loadDataRequestRepository.findById(id));
    }

    @PostMapping("/create-check-job")
    public ResponseEntity createCheckJob(@RequestBody CheckDTO checkDTO){

        URI uri = URI.create(loaderService.sendDataRequest(checkDTO.getCity(), checkDTO.getLat(), checkDTO.getLon()));
        return ResponseEntity.created(uri).build();
    }
}
