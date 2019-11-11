package com.akulinski.crimetivitystoreservice.mock;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeEventPrimaryKey;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeType;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;
import java.util.stream.Stream;

@Configuration
@Profile("mock-data")
public class CrimeEventMock {

    private CrimeEventRepository crimeEventRepository;

    private final Faker faker = Faker.instance();

    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${mock.count:100}")
    private String mockData;

    public CrimeEventMock(CrimeEventRepository crimeEventRepository) {
        this.crimeEventRepository = crimeEventRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void mockCrimeEvent() {
        if(crimeEventRepository.count()<100) {
            Stream.generate(() -> {
                CrimeEvent crimeEvent = new CrimeEvent();
                CrimeEventPrimaryKey crimeEventPrimaryKey = new CrimeEventPrimaryKey("krakow", CrimeType.ROBBERY, faker.date().between(DateUtils.addDays(new Date(), -60), new Date()));
                crimeEvent.setLongitude(BigDecimal.valueOf(secureRandom.nextInt(100)));
                crimeEvent.setLatitude(BigDecimal.valueOf(secureRandom.nextInt(100)));
                crimeEvent.setPrimaryKey(crimeEventPrimaryKey);

                return crimeEvent;
            }).limit(Long.parseLong(mockData)).forEach(crimeEvent -> crimeEventRepository.save(crimeEvent));
        }
    }
}
