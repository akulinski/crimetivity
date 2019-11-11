package com.akulinski.crimetivitystoreservice.core.repositories;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeEventPrimaryKey;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeType;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CrimeEventRepository extends CassandraRepository<CrimeEvent, CrimeEventPrimaryKey> {
    List<CrimeEvent> findByPrimaryKey_City(String city);

    List<CrimeEvent> findByPrimaryKey_CityAndPrimaryKey_CrimeTypeAndPrimaryKey_DateBetween(String city, CrimeType crimeType, Date start, Date end);

    List<CrimeEvent> findByPrimaryKey_CityAndPrimaryKey_CrimeTypeInAndPrimaryKey_DateBetween(String city, Set<CrimeType> crimeType, Date start, Date end);

}
