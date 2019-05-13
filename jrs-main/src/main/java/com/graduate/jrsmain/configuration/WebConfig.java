package com.graduate.jrsmain.configuration;

import com.graduate.jrsmain.interceptor.Oauth2Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Oauth2Interceptor oauth2Interceptor(){
        return new Oauth2Interceptor();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(oauth2Interceptor()).addPathPatterns("/**");
    }
}
