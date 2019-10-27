package com.akulinski.crimetivitystoreservice.mock;

import com.akulinski.crimetivitystoreservice.core.domain.CrimeEvent;
import com.akulinski.crimetivitystoreservice.core.domain.CrimeType;
import com.akulinski.crimetivitystoreservice.core.repositories.CrimeEventRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.stream.Stream;

@Configuration
@Profile("mock-data")
public class CrimeEventMock {

    private CrimeEventRepository crimeEventRepository;

    private final Faker faker = Faker.instance();

    private final SecureRandom secureRandom = new SecureRandom();

    public CrimeEventMock(CrimeEventRepository crimeEventRepository) {
        this.crimeEventRepository = crimeEventRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void mockCrimeEvent() {
        Stream.generate(()->{
            CrimeEvent crimeEvent = new CrimeEvent();
            crimeEvent.setCity("krakow");
            crimeEvent.setCrimeType(CrimeType.ROBBERY);
            crimeEvent.setLongitude(BigDecimal.valueOf(secureRandom.nextInt(1000)));
            crimeEvent.setLatitude(BigDecimal.valueOf(secureRandom.nextInt(1000)));
            return crimeEvent;
        }).limit(1000).forEach(crimeEvent -> crimeEventRepository.save(crimeEvent).subscribe());
    }
}
