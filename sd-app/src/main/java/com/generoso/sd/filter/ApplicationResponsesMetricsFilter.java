package com.generoso.sd.filter;

import com.generoso.sd.metrics.MetricsService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ApplicationResponsesMetricsFilter implements Filter {

    private final MetricsService metricsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws ServletException, IOException {

        chain.doFilter(request, response);

        var httpServletRequest = (HttpServletRequest) request;
        var path = httpServletRequest.getRequestURI();
        var method = httpServletRequest.getMethod();
        var statusCode = String.valueOf(((HttpServletResponse) response).getStatus());
        metricsService.applicationResponseTotal(method, path, statusCode);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // nothing to do
    }

    @Override
    public void destroy() {
        // nothing to do
    }
}
