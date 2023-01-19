package com.indocyber.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){

        registry.addViewController("/").setViewName("forward:my-login/index");
        registry.addViewController("/author").setViewName("forward:autho/index");
        registry.addViewController("/customer").setViewName("forward:customer/index");
        registry.addViewController("/book").setViewName("forward:book/index");
        registry.addViewController("/loan").setViewName("forward:loan/index");
    }

}