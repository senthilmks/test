package com.test.security;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * RequestBlockingFilter class to block requests to MovieHut.com.
 */

public class RequestHandlingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestBlockingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        //UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        String userAgent = req.getHeader("User-Agent");
        System.out.println("userAgent >>"+userAgent);
        chain.doFilter(request, response);
//        if (userAgent.getBrowser().getName().equals("Firefox")) {
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>You are not authorized to enter</font>");
//        } else {
//            chain.doFilter(request, response);
//        }
    }

    public void destroy() {
        //we can close resources here
    }

}