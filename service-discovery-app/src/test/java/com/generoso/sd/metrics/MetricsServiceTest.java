package com.generoso.sd.metrics;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusCounter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MetricsServiceTest {

    private MeterRegistry meterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    private MetricsService metricsService = new MetricsService(meterRegistry);

    @Test
    void thenCallingServiceToRegisterApplicationResponseTotal_shouldIncreaseMetric() {
        // Arrange
        String method = "GET";
        String path = "/example";
        String status = "200";

        // Act
        metricsService.applicationResponseTotal(method, path, status);

        // Assert
        assertThat(getCounterValue(meterRegistry, "application.responses.total",
                new String[]{"method", "path", "status"}, "GET", "/example", "200"))
                .isPresent()
                .hasValue(1.0);
    }

    private static Optional<Double> getCounterValue(MeterRegistry registry, String name, String[] labelNames, String... values) {
        Tags tags = getTags(labelNames, values);
        return registry.getMeters().stream().filter(
                        m -> m.getId().getName().equals(name) && m.getId().getTags()
                                .containsAll(tags.stream().toList())).findFirst()
                .map(metric -> ((PrometheusCounter) metric).count());
    }

    private static Tags getTags(String[] labelNames, String[] values) {
        Tags tags = Tags.empty();
        int idx = 0;
        for (String label : labelNames) {
            tags = tags.and(Tag.of(label, values[idx++]));
        }

        return Tags.of(tags);
    }
}