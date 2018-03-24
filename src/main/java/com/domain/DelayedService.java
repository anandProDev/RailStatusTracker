package com.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delayedService")
public class DelayedService {

    private String trainNumber;
    private String source;
    private String destination;
    private String lengthOfDelay;
    private String status;

    public DelayedService() {
    }

    public DelayedService(String trainNumber, String source, String destination, String lengthOfDelay, String status) {
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.lengthOfDelay = lengthOfDelay;
        this.status = status;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLengthOfDelay() {
        return lengthOfDelay;
    }

    public void setLengthOfDelay(String lengthOfDelay) {
        this.lengthOfDelay = lengthOfDelay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
