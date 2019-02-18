package com.example.demo;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


@Component
public class SimpleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter
            (ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Remote Host: "+request.getRemoteHost());
        System.out.println("Remote Address: "+request.getRemoteAddr());
        chain.doFilter(request,response);
        System.out.println("Local Name: "+request.getLocalName());
        System.out.println("Local Address: "+request.getLocalAddr());
    }

    @Override
    public void destroy() {

    }
}
