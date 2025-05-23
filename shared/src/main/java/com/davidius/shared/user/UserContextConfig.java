package com.davidius.shared.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserContextConfig implements WebMvcConfigurer {
    private final UserHeaderInterceptor userHeaderInterceptor;

    public UserContextConfig(UserHeaderInterceptor userHeaderInterceptor) {
        this.userHeaderInterceptor = userHeaderInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userHeaderInterceptor);
    }
}