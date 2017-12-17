package com.epam.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {

    public WebAppInitialiser() {
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, SwaggerConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}