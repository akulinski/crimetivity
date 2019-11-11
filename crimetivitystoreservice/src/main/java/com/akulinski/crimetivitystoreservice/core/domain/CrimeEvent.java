package com.akulinski.crimetivitystoreservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Table("crime_event")
@AllArgsConstructor
@NoArgsConstructor
public class CrimeEvent {

    @PrimaryKey
    private CrimeEventPrimaryKey primaryKey;

    @Column
    private BigDecimal longitude;

    @Column
    private BigDecimal latitude;


    public CrimeEvent(String city, BigDecimal longitude, BigDecimal latitude, CrimeType crimeType, Date date) {
        this.primaryKey = new CrimeEventPrimaryKey(city, crimeType, date);
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
