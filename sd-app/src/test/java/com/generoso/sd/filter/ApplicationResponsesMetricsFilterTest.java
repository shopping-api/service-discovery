package com.generoso.sd.filter;

import com.generoso.sd.metrics.MetricsService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ApplicationResponsesMetricsFilterTest {

    @MockBean
    private MetricsService metricsService;

    @Test
    void shouldIncreaseTheRequestMetricCounter() throws ServletException, IOException {
        // Arrange
        var filter = new ApplicationResponsesMetricsFilter(metricsService);

        var request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/unit-test");
        var response = new MockHttpServletResponse();
        response.setStatus(200);
        var filterChain = new MockFilterChain();

        // Act
        filter.doFilter(request, response, filterChain);

        // Assert
        verify(metricsService).applicationResponseTotal("GET", "/unit-test", "200");
    }
}
