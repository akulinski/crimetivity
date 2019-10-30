package com.akulinski.crimetivity.crimefilteringservice.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CrimeEvent implements Serializable {

    private String city;

    private BigDecimal longitude;

    private BigDecimal latitude;
}
