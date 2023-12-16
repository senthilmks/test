package com.test.security;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

//@javax.servlet.annotation.WebFilter(filterName = "performanceFilter", urlPatterns = {"/*"})
public class PerformanceFilter implements javax.servlet.Filter {

//  @javax.inject.Inject
  private PerformanceStatisticsBean performanceBean;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain) throws IOException, ServletException {
    long startTime = System.currentTimeMillis();
    chain.doFilter(request, response);
    long endTime = System.currentTimeMillis();
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      //String resourceName = httpRequest.getPathInfo();
      String resourceName = httpRequest.getLocalAddr();
      long time = endTime - startTime;
      performanceBean.logTime(resourceName, time);
    }
  }

  @Override
  public void destroy() {
  }
}