package com.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties({"service_timetable", "category", "mode", "service", ""})
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainDetails {

    @JsonProperty(value = "train_uid", required = true)
    String train_uid;

    @JsonProperty(value = "destination_name", required = true)
    String destination_name;

    @JsonProperty(value = "aimed_departure_time", required = true)
    String aimed_departure_time;

    @JsonProperty(value = "aimed_arrival_time", required = true)
    String aimed_arrival_time;

    @JsonProperty(value = "status", required = true)
    String status;


    @JsonProperty(value = "expected_arrival_time", required = true)
    String expected_arrival_time;

    @JsonProperty(value = "expected_departure_time", required = true)
    String expected_departure_time;

    @JsonProperty(value = "best_arrival_estimate_mins", required = true)
    String best_arrival_estimate_mins;

    @JsonProperty(value = "best_departure_estimate_mins", required = true)
    String best_departure_estimate_mins;


    public String getTrain_uid() {
        return train_uid;
    }

    public void setTrain_uid(String train_uid) {
        this.train_uid = train_uid;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public String getAimed_departure_time() {
        return aimed_departure_time;
    }

    public void setAimed_departure_time(String aimed_departure_time) {
        this.aimed_departure_time = aimed_departure_time;
    }

    public String getAimed_arrival_time() {
        return aimed_arrival_time;
    }

    public void setAimed_arrival_time(String aimed_arrival_time) {
        this.aimed_arrival_time = aimed_arrival_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpected_arrival_time() {
        return expected_arrival_time;
    }

    public void setExpected_arrival_time(String expected_arrival_time) {
        this.expected_arrival_time = expected_arrival_time;
    }

    public String getExpected_departure_time() {
        return expected_departure_time;
    }

    public void setExpected_departure_time(String expected_departure_time) {
        this.expected_departure_time = expected_departure_time;
    }

    public String getBest_arrival_estimate_mins() {
        return best_arrival_estimate_mins;
    }

    public void setBest_arrival_estimate_mins(String best_arrival_estimate_mins) {
        this.best_arrival_estimate_mins = best_arrival_estimate_mins;
    }

    public String getBest_departure_estimate_mins() {
        return best_departure_estimate_mins;
    }

    public void setBest_departure_estimate_mins(String best_departure_estimate_mins) {
        this.best_departure_estimate_mins = best_departure_estimate_mins;
    }
}
