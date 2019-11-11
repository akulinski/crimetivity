package com.akulinski.crimetivitystoreservice.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;

@PrimaryKeyClass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrimeEventPrimaryKey implements Serializable {

    @PrimaryKeyColumn(name = "city", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String city;

    @PrimaryKeyColumn(name = "crime_type", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private CrimeType crimeType;

    @PrimaryKeyColumn(name = "date", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private Date date;

}
