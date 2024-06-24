package com.generoso.ft.sd.client;

import lombok.AllArgsConstructor;
import org.hawkular.agent.prometheus.text.TextPrometheusMetricsProcessor;
import org.hawkular.agent.prometheus.types.MetricFamily;
import org.hawkular.agent.prometheus.walkers.CollectorPrometheusMetricsWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class MetricsClientRequest {

    private final PrivateMetricsRequestTemplate privateMetricsRequestTemplate;

    private final Client client;

    public List<MetricFamily> collectMetrics() {
        var response = client.execute(privateMetricsRequestTemplate);
        return parsePrometheusMetrics(response.body());
    }

    private List<MetricFamily> parsePrometheusMetrics(String metricEndpointResponseBody) {
        var inputStream = new ByteArrayInputStream(metricEndpointResponseBody.getBytes(StandardCharsets.UTF_8));

        var collector = new CollectorPrometheusMetricsWalker();
        var processor = new TextPrometheusMetricsProcessor(inputStream, collector);
        processor.walk();

        return collector.getAllMetricFamilies();
    }
}
