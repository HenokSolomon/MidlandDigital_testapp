package com.midlanddigital.test.app;

import com.midlanddigital.test.app.api.StaffUUIdHttpHeaderInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private StaffUUIdHttpHeaderInterceptor staffUUIdHttpHeaderInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(staffUUIdHttpHeaderInterceptor).addPathPatterns("/patient/**");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
