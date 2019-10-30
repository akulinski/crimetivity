package com.akulinski.crimetivity.crimefilteringservice.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilteringRequest implements Serializable {

    private String id;

    private String lat;

    private String lon;

    private List<CrimeEvent> crimeEvents;

}
