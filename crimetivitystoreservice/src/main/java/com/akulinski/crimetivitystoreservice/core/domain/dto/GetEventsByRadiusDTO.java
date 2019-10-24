package com.akulinski.crimetivitystoreservice.core.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetEventsByRadiusDTO {

    private String city;

    private Long radius;

    private BigDecimal longitude;

    private BigDecimal latitude;

}
