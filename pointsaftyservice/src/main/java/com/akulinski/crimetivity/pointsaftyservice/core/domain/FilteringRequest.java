package com.akulinski.crimetivity.pointsaftyservice.core.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class FilteringRequest {

    private String id;

    private String lat;

    private String lon;

    private List<CrimeEvent> crimeEvents;

}
