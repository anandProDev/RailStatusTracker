package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class RailStatusTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailStatusTrackerApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){

        RestTemplate template = new RestTemplate();
        return template;
    }
}
