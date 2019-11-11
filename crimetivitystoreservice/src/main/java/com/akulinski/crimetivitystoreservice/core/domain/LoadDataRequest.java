package com.akulinski.crimetivitystoreservice.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Data
public class LoadDataRequest implements Serializable {

    private String id;

    private String cityName;

    private String lat;

    private String lon;

    private Instant from;

    private Set<CrimeType> crimeTypes;

    private Instant to;

    private SafetyStatus safetyStatus;

    private RequestStatus requestStatus;
}
