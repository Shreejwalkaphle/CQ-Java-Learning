package com.example.Integration2.configurations;

import com.example.Integration2.filters.RequestValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean(name = "customRequestValidationFilter")
    public FilterRegistrationBean<RequestValidationFilter> requestValidationFilter(RequestValidationFilter filter) {
        FilterRegistrationBean<RequestValidationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/replace this whole string with the api for which this filter is to be applied");
        return registrationBean;
    }
}
