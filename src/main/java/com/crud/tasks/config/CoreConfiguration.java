package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@Getter
@EnableSwagger2
public class CoreConfiguration {

    //RestTemplate restTemplate= new RestTemplate();
    @Bean
    public RestTemplate restTemplate() {
        //List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        //messageConverters.add(converter);
        //restTemplate.setMessageConverters(messageConverters);

        return new RestTemplate();
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.crud.tasks.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}


