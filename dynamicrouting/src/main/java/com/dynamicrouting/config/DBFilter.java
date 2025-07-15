package com.dynamicrouting.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DBFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Read the DB name from the request header
        String dbName = request.getHeader("X-DB-Name");

        if (dbName == null || dbName.isEmpty()) {
            dbName = "maindb";
        }

        // Set the DB context
        DBContextHolder.setDB(dbName.toLowerCase());

        try {
            // Set the same DB name in the response header
            response.setHeader("X-DB-Name", dbName.toLowerCase());

            // Continue the filter chain
            filterChain.doFilter(request, response);
        } finally {
            // Clear context to avoid memory leak/thread local issues
            DBContextHolder.clear();
        }
    }
}