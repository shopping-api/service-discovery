package com.generoso.sd.config;

import com.generoso.sd.filter.ApplicationResponsesMetricsFilter;
import com.generoso.sd.filter.RequestLoggingFilter;
import com.generoso.sd.metrics.MetricsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FiltersConfig.class})
class FilterConfigTest {

    @MockBean
    private MetricsService metricsService;

    @Autowired
    private FilterRegistrationBean<RequestLoggingFilter> incomingRequestLogFilter;

    @Autowired
    private FilterRegistrationBean<ApplicationResponsesMetricsFilter> applicationResponsesMetricsFilter;

    @Test
    void shouldCreateFiltersInCorrectOrder() {
        assertThat(incomingRequestLogFilter.getOrder()).isZero();
        assertThat(applicationResponsesMetricsFilter.getOrder()).isEqualTo(1);
    }
}
