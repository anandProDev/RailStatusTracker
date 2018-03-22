package com.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delayedService")
public class DelayedService {

    private String trainNumber;
    private String source;
    private String destination;
    private String lengthOfDelay;
    private String status;

    public DelayedService(String trainNumber, String source, String destination, String lengthOfDelay, String status) {
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.lengthOfDelay = lengthOfDelay;
        this.status = status;
    }
}
