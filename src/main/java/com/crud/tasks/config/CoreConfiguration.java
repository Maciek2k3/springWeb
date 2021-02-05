package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@Getter
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

}


