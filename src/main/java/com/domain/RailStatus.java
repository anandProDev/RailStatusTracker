package com.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * live response schema.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "time_of_day",
        "request_time",
        "station_name",
        "station_code",
        "railDetailsHolder"
})
public class RailStatus {

    /**
     * The date of interest specified in the request in yyyy-dd-mm format.
     * (Required)
     *
     */
    @JsonProperty("date")
    @JsonPropertyDescription("The date of interest specified in the request in yyyy-dd-mm format.")
    private String date;
    /**
     * The time of interest specified in the request in hh:mm format.
     * (Required)
     *
     */
    @JsonProperty("time_of_day")
    @JsonPropertyDescription("The time of interest specified in the request in hh:mm format.")
    private String timeOfDay;
    /**
     * The time of the request in ISO 8601 format.
     * (Required)
     *
     */
    @JsonProperty("request_time")
    @JsonPropertyDescription("The time of the request in ISO 8601 format.")
    private String requestTime;
    /**
     * The name of the station of interest.
     * (Required)
     *
     */
    @JsonProperty("station_name")
    @JsonPropertyDescription("The name of the station of interest.")
    private String stationName;
    /**
     * The station code of the station of interest as specified in the request.
     * (Required)
     *
     */
    @JsonProperty("station_code")
    @JsonPropertyDescription("The station code of the station of interest as specified in the request.")
    private String stationCode;
    /**
     * Holds a collection of the requested train railDetailsHolder.
     * (Required)
     *
     */
    @JsonProperty("railDetailsHolder")
    @JsonPropertyDescription("Holds a collection of the requested train railDetailsHolder.")
    private RailDetailsHolder railDetailsHolder;

    /**
     * The date of interest specified in the request in yyyy-dd-mm format.
     * (Required)
     *
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * The date of interest specified in the request in yyyy-dd-mm format.
     * (Required)
     *
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * The time of interest specified in the request in hh:mm format.
     * (Required)
     *
     */
    @JsonProperty("time_of_day")
    public String getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * The time of interest specified in the request in hh:mm format.
     * (Required)
     *
     */
    @JsonProperty("time_of_day")
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    /**
     * The time of the request in ISO 8601 format.
     * (Required)
     *
     */
    @JsonProperty("request_time")
    public String getRequestTime() {
        return requestTime;
    }

    /**
     * The time of the request in ISO 8601 format.
     * (Required)
     *
     */
    @JsonProperty("request_time")
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * The name of the station of interest.
     * (Required)
     *
     */
    @JsonProperty("station_name")
    public String getStationName() {
        return stationName;
    }

    /**
     * The name of the station of interest.
     * (Required)
     *
     */
    @JsonProperty("station_name")
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * The station code of the station of interest as specified in the request.
     * (Required)
     *
     */
    @JsonProperty("station_code")
    public String getStationCode() {
        return stationCode;
    }

    /**
     * The station code of the station of interest as specified in the request.
     * (Required)
     *
     */
    @JsonProperty("station_code")
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    /**
     * Holds a collection of the requested train railDetailsHolder.
     * (Required)
     *
     */
    @JsonProperty("departures")
    public RailDetailsHolder getRailDetailsHolder() {
        return railDetailsHolder;
    }

    /**
     * Holds a collection of the requested train railDetailsHolder.
     * (Required)
     *
     */
    @JsonProperty("departures")
    public void setRailDetailsHolder(RailDetailsHolder railDetailsHolder) {
        this.railDetailsHolder = railDetailsHolder;
    }

    @Override
    public String toString() {
        return "RailStatus{" +
                "date='" + date + '\'' +
                ", timeOfDay='" + timeOfDay + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", railDetailsHolder=" + railDetailsHolder +
                '}';
    }
}
