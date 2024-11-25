package com.petroleumserver.interceptor;

import com.petroleumcommom.constant.JwtClaimsConstant;
import com.petroleumcommom.context.BaseContext;
import com.petroleumcommom.properties.JwtProperties;
import com.petroleumcommom.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private JwtProperties jwtProperties;

     
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if (!(handler instanceof HandlerMethod)) {
            
            return true;
        }

        
        String token = request.getHeader(jwtProperties.getUserTokenName());

        
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            
            log.info("当前员工id：{}", empId);
            BaseContext.setCurrentId(empId);
            
            return true;
        } catch (Exception ex) {
            
            response.setStatus(401);
            return false;
        }
    }
}
