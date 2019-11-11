package com.akulinski.crimetivitystoreservice.core.domain.dto;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeType;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class GetCrimesBetweenDatesDTO implements Serializable {
    private String city;
    private Instant from;
    private Instant to;
    private CrimeType crimeType;
}
