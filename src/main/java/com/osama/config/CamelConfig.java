package com.osama.config;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig extends RouteBuilder {
    @Override
    public void configure() {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .apiProperty("api.title", "Translation Management API")
                .apiProperty("api.version", "1.0.0");
    }
}