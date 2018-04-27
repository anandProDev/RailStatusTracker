package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com")
public class RailStatusTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailStatusTrackerApplication.class, args);
    }

//    @Bean
//    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
//                                       MongoMappingContext context) {
//
//        MappingMongoConverter converter =
//                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
//
//        return mongoTemplate;
//
//    }
}
