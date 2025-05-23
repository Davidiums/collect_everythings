package com.davidius.shared.user;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Configuration
public class UserHeaderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserContext user = new UserContext();
        user.setMail(request.getHeader("X-User-Mail"));
        user.setFirstName(request.getHeader("X-User-First-Name"));
        user.setLastName(request.getHeader("X-User-Last-Name"));
        user.setRole(request.getHeader("X-User-Role"));
        user.setSubject(request.getHeader("X-User-Display-Name")); // ou autre header selon ta gateway
        String idStr = request.getHeader("X-User-Id");
        if (idStr != null) {
            try { user.setId(Long.parseLong(idStr)); } catch (Exception ignore) {}
        }
        UserContext.set(user);
        return true;
    }

}