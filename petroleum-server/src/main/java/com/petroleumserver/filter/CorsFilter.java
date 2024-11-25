package com.petroleumserver.filter;

import com.petroleumcommom.constant.Const;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(Const.ORDER_CORS)
public class CorsFilter extends HttpFilter {

    @Value("*")
    String origin;

    @Value("false")
    boolean credentials;

    @Value("*")
    String methods;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        this.addCorsHeader(request, response);
        chain.doFilter(request, response);
    }


    private void addCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", this.resolveOrigin(request));
        response.addHeader("Access-Control-Allow-Methods", this.resolveMethod());
        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        if(credentials) {
            response.addHeader("Access-Control-Allow-Credentials", "true");
        }
    }


    private String resolveMethod(){
        return methods.equals("*") ? "GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE, PATCH" : methods;
    }


    private String resolveOrigin(HttpServletRequest request){
        return origin.equals("*") ? request.getHeader("Origin") : origin;
    }
}