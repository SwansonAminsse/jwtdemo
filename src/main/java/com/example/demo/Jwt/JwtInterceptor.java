package com.example.demo.Jwt;

import com.example.demo.common.BusinessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            JwtRequired jwtRequired = handlerMethod.getMethodAnnotation(JwtRequired.class);
            if (jwtRequired == null) {
                jwtRequired = handlerMethod.getBeanType().getAnnotation(JwtRequired.class);
            }

            if (jwtRequired != null) {

                String token = request.getHeader("Authorization");

                try {
                    JwtUtil.verify(token);
                    return true;
                } catch (BusinessException e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return false;
                }
            }
        }

        return true;
    }
}
