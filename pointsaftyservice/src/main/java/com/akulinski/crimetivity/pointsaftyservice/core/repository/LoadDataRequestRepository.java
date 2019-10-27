package com.akulinski.crimetivity.pointsaftyservice.core.repository;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.LoadDataRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoadDataRequestRepository extends MongoRepository<LoadDataRequest, String> {
}
