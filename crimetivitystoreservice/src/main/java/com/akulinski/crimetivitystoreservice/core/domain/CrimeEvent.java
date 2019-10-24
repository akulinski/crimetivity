package com.akulinski.crimetivitystoreservice.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class CrimeEvent {

    @Id
    private String id;

    private String city;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private CrimeType crimeType;

}
