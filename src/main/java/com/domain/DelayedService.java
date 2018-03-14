package com.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delayedServices")
public class DelayedService {

    @Id
    String date;

    private String trainNumber;
    private String source;
    private String destination;
    private String lengthOfDelay;
    private String status;

}
