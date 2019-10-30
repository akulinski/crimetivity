package com.akulinski.crimetivity.pointsaftyservice.core.repository;

import com.akulinski.crimetivity.pointsaftyservice.core.domain.FilteringRequest;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilteringRequestRepository extends MongoRepository<FilteringRequest, String> {
}
