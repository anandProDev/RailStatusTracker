package com.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delayedService")
public class DelayedService {

    private String trainNumber;
    private String source;
    private String destination;
    private String lengthOfDelay;
    private String status;
    private String expectedDepartureTime;
    private String expectedArrivalTime;
    private String railCompany;
    private String aimedDepartureTime;
    private String aimedArrivalTime;

    public DelayedService() {
    }

    public DelayedService(String trainNumber, String source, String destination, String lengthOfDelay, String status, String expectedDepartureTime, String expectedArrivalTime, String railCompany, String aimedDepartureTime, String aimedArrivalTime) {
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.lengthOfDelay = lengthOfDelay;
        this.status = status;
        this.expectedDepartureTime = expectedDepartureTime;
        this.expectedArrivalTime = expectedArrivalTime;
        this.railCompany = railCompany;
        this.aimedDepartureTime = aimedDepartureTime;
        this.aimedArrivalTime = aimedArrivalTime;
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

    public String getExpectedDepartureTime() {
        return expectedDepartureTime;
    }

    public void setExpectedDepartureTime(String expectedDepartureTime) {
        this.expectedDepartureTime = expectedDepartureTime;
    }

    public String getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public void setExpectedArrivalTime(String expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public String getRailCompany() {
        return railCompany;
    }

    public void setRailCompany(String railCompany) {
        this.railCompany = railCompany;
    }

    public String getAimedDepartureTime() {
        return aimedDepartureTime;
    }

    public void setAimedDepartureTime(String aimedDepartureTime) {
        this.aimedDepartureTime = aimedDepartureTime;
    }

    public String getAimedArrivalTime() {
        return aimedArrivalTime;
    }

    public void setAimedArrivalTime(String aimedArrivalTime) {
        this.aimedArrivalTime = aimedArrivalTime;
    }


    public static final class DelayedServiceBuilder {
        private String trainNumber;
        private String source;
        private String destination;
        private String lengthOfDelay;
        private String status;
        private String expectedDepartureTime;
        private String expectedArrivalTime;
        private String railCompany;
        private String aimedDepartureTime;
        private String aimedArrivalTime;

        private DelayedServiceBuilder() {
        }

        public static DelayedServiceBuilder aDelayedService() {
            return new DelayedServiceBuilder();
        }

        public DelayedServiceBuilder withTrainNumber(String trainNumber) {
            this.trainNumber = trainNumber;
            return this;
        }

        public DelayedServiceBuilder withSource(String source) {
            this.source = source;
            return this;
        }

        public DelayedServiceBuilder withDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public DelayedServiceBuilder withLengthOfDelay(String lengthOfDelay) {
            this.lengthOfDelay = lengthOfDelay;
            return this;
        }

        public DelayedServiceBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public DelayedServiceBuilder withExpectedDepartureTime(String expectedDepartureTime) {
            this.expectedDepartureTime = expectedDepartureTime;
            return this;
        }

        public DelayedServiceBuilder withExpectedArrivalTime(String expectedArrivalTime) {
            this.expectedArrivalTime = expectedArrivalTime;
            return this;
        }

        public DelayedServiceBuilder withRailCompany(String railCompany) {
            this.railCompany = railCompany;
            return this;
        }

        public DelayedServiceBuilder withAimedDepartureTime(String aimedDepartureTime) {
            this.aimedDepartureTime = aimedDepartureTime;
            return this;
        }

        public DelayedServiceBuilder withAimedArrivalTime(String aimedArrivalTime) {
            this.aimedArrivalTime = aimedArrivalTime;
            return this;
        }

        public DelayedService build() {
            DelayedService delayedService = new DelayedService();
            delayedService.setTrainNumber(trainNumber);
            delayedService.setSource(source);
            delayedService.setDestination(destination);
            delayedService.setLengthOfDelay(lengthOfDelay);
            delayedService.setStatus(status);
            delayedService.setExpectedDepartureTime(expectedDepartureTime);
            delayedService.setExpectedArrivalTime(expectedArrivalTime);
            delayedService.setRailCompany(railCompany);
            delayedService.setAimedDepartureTime(aimedDepartureTime);
            delayedService.setAimedArrivalTime(aimedArrivalTime);
            return delayedService;
        }
    }
}
