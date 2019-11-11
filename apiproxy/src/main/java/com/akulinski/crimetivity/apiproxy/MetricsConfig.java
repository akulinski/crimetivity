package com.akulinski.crimetivity.apiproxy;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MetricsConfig {

    private static final Duration HISTOGRAM_EXPIRY = Duration.ofMinutes(10);

    private static final Duration STEP = Duration.ofSeconds(5);

    @Value("localhost")
    private String hostId;

    @Value("apigateway")
    private String serviceId;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags("host", hostId, "service", serviceId)
                .meterFilter(MeterFilter.deny(id -> {
                    String uri = id.getTag("uri");
                    return uri != null && uri.startsWith("/swagger");
                }))
                .meterFilter(new MeterFilter() {
                    @Override
                    public DistributionStatisticConfig configure(Meter.Id id,
                                                                 DistributionStatisticConfig config) {
                        return config.merge(DistributionStatisticConfig.builder()
                                .percentilesHistogram(true)
                                .percentiles(0.5, 0.75, 0.95)
                                .expiry(HISTOGRAM_EXPIRY)
                                .bufferLength((int) (HISTOGRAM_EXPIRY.toMillis() / STEP.toMillis()))
                                .build());
                    }
                });
    }

}
