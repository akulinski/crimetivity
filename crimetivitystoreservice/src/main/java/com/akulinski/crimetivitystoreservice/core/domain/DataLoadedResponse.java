package com.akulinski.crimetivitystoreservice.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class DataLoadedResponse implements Serializable {

    private String requestId;

    private List<CrimeEvent> crimeEventList;

    private String city;

    private Instant timestamp;
}
