package com.generoso.sd.config;

import com.generoso.sd.filter.ApplicationResponsesMetricsFilter;
import com.generoso.sd.filter.IncomingRequestLogFilter;
import com.generoso.sd.metrics.MetricsService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<IncomingRequestLogFilter> incomingRequestLogFilter() {
        var filter = new FilterRegistrationBean<>(new IncomingRequestLogFilter());
        filter.setOrder(0);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<ApplicationResponsesMetricsFilter> responseMetricFilter(
            MetricsService metricsService) {
        var filter = new FilterRegistrationBean<>(new ApplicationResponsesMetricsFilter(metricsService));
        filter.setOrder(1);
        return filter;
    }
}
