package com.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


//@JsonIgnoreProperties({"date", "request_time", "station_code"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class RailStatus {

    @JsonIgnoreProperties(value = "date")
    String date;

    @JsonProperty(value = "station_name", required=true)
    String station_name;

    @JsonProperty(value = "station_code", required=true)
    String station_code;

    @JsonProperty(value = "time_of_day", required=true)
    String time_of_day;

    @JsonProperty(value = "departures", required=true)
    String departures;

    public String getDepartures() {
        return departures;
    }

    public void setDepartures(String departures) {
        this.departures = departures;
    }

    //    @JsonProperty(value = "departures", required = true)
//    ArrayList<TrainDetails> departures;
//
//    public ArrayList<TrainDetails> getDepartures() {
//        return departures;
//    }
//
//    public void setDepartures(ArrayList<TrainDetails> departures) {
//        this.departures = departures;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public String getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(String time_of_day) {
        this.time_of_day = time_of_day;
    }
}
