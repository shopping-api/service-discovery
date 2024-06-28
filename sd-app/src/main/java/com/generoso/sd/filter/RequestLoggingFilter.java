package com.generoso.sd.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        var httpServletRequest = (HttpServletRequest) request;
        var httpServletResponse = (HttpServletResponse) response;
        var path = httpServletRequest.getRequestURI();
        var method = httpServletRequest.getMethod();

        log.info("Incoming request {} {}", method, path);
        chain.doFilter(request, response);
        log.info("Returning request with status code: {}", httpServletResponse.getStatus());
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
