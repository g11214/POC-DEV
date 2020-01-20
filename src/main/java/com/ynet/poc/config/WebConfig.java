package com.ynet.poc.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ynet.poc.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor initAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initAuthInterceptor())
                .addPathPatterns("/api/**")
                .addPathPatterns("/addr/**")
                .addPathPatterns("/cart/**")
                .addPathPatterns("/order/**")
                .excludePathPatterns("/api/auth")
                .excludePathPatterns("/api/auth/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
