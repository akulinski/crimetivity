package com.akulinski.crimetivitystoreservice.core.domain.dto;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrimeEventDTO {

    private String city;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private CrimeType crimeType;
}
