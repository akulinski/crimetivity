package com.akulinski.crimetivity.pointsaftyservice.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CrimeEvent implements Serializable {

    private String city;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date date;
}
