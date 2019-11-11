package com.akulinski.crimetivity.pointsaftyservice.core.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
@Document
public class LoadDataRequest implements Serializable {

    private String id;

    private String cityName;

    private String lat;

    private String lon;

    private Instant from;

    private Instant to;

    private Set<CrimeType> crimeTypes;

    private SafetyStatus safetyStatus;

    private RequestStatus requestStatus;
}
