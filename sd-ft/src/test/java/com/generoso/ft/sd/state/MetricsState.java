package com.generoso.ft.sd.state;

import io.cucumber.spring.ScenarioScope;
import lombok.Getter;
import lombok.Setter;
import org.hawkular.agent.prometheus.types.MetricFamily;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ScenarioScope
public class MetricsState {

    private List<MetricFamily> initialMetrics;

    private List<MetricFamily> newMetrics;
}
