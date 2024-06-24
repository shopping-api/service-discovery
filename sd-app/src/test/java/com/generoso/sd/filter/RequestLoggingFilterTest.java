package com.generoso.sd.filter;

import ch.qos.logback.classic.Level;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static com.generoso.sd.utils.LogUtils.assertMessageWasInLogs;
import static com.generoso.sd.utils.LogUtils.getListAppenderForClass;

class RequestLoggingFilterTest {

    @Test
    void shouldIncludeALogLine() throws ServletException, IOException {
        // Arrange
        var listAppender = getListAppenderForClass(RequestLoggingFilter.class);

        var filter = new RequestLoggingFilter();

        var request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/unit-test");

        var response = new MockHttpServletResponse();
        response.setStatus(200);

        // Act
        filter.doFilter(request, response, new MockFilterChain());

        // Assert
        assertMessageWasInLogs(listAppender, "Incoming request GET /unit-test", Level.INFO);
        assertMessageWasInLogs(listAppender, "Returning request with status code: 200", Level.INFO);
    }
}
