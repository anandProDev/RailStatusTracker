package com.domain;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "service_timetable",
        "mode",
        "category",
        "service",
        "train_uid",
        "platform",
        "operator",
        "aimed_departure_time",
        "aimed_arrival_time",
        "aimed_pass_time",
        "origin_name",
        "destination_name",
        "source",
        "status",
        "expected_arrival_time",
        "expected_departure_time",
        "best_arrival_estimate_mins",
        "best_departure_estimate_mins"
})
public class RailDetail {

    @JsonProperty("service_timetable")
    private Object serviceTimetable;
    /**
     * The mode of the service: 'train' for regular services and 'bus' for replacement bus services.
     *
     */
    @JsonProperty("mode")
    @JsonPropertyDescription("The mode of the service: 'train' for regular services and 'bus' for replacement bus services.")
    private String mode;
    /**
     * The category of the service: please refer to [full list](http://nrodwiki.rockshore.net/index.php/CIF_Codes#Train_Category) of possible values.
     *
     */
    @JsonProperty("category")
    @JsonPropertyDescription("The category of the service: please refer to [full list](http://nrodwiki.rockshore.net/index.php/CIF_Codes#Train_Category) of possible values.")
    private String category;
    /**
     * The service code of the service.
     *
     */
    @JsonProperty("service")
    @JsonPropertyDescription("The service code of the service.")
    private String service;
    /**
     * The train uid of the service.
     *
     */
    @JsonProperty("train_uid")
    @JsonPropertyDescription("The train uid of the service.")
    private String trainUid;
    /**
     * The platform at which the service would call at or pass through, if available.
     *
     */
    @JsonProperty("platform")
    @JsonPropertyDescription("The platform at which the service would call at or pass through, if available.")
    private String platform;
    /**
     * The ATOC code of the train operating company operating the service.
     *
     */
    @JsonProperty("operator")
    @JsonPropertyDescription("The ATOC code of the train operating company operating the service.")
    private String operator;
    /**
     * The aimed departure time of the service in hh:mm format according to the timetables. null if the service doesn't depart from the station of interest e.g. passing services.
     *
     */
    @JsonProperty("aimed_departure_time")
    @JsonPropertyDescription("The aimed departure time of the service in hh:mm format according to the timetables. null if the service doesn't depart from the station of interest e.g. passing services.")
    private String aimedDepartureTime;
    /**
     * The aimed arrival time of the service in hh:mm format according to the timetables. null if the service doesn't arrive at the station of interest e.g. passing services.
     *
     */
    @JsonProperty("aimed_arrival_time")
    @JsonPropertyDescription("The aimed arrival time of the service in hh:mm format according to the timetables. null if the service doesn't arrive at the station of interest e.g. passing services.")
    private String aimedArrivalTime;
    /**
     * The aimed pass time of the service in hh:mm format according to the timetables. null if the service doesn't pass through the station of interest, e.g. arrivals and railDetailsHolder.
     *
     */
    @JsonProperty("aimed_pass_time")
    @JsonPropertyDescription("The aimed pass time of the service in hh:mm format according to the timetables. null if the service doesn't pass through the station of interest, e.g. arrivals and railDetailsHolder.")
    private String aimedPassTime;
    /**
     * The name of the origin station for the service
     *
     */
    @JsonProperty("origin_name")
    @JsonPropertyDescription("The name of the origin station for the service")
    private String originName;
    /**
     * The name of the destination station for the service
     *
     */
    @JsonProperty("destination_name")
    @JsonPropertyDescription("The name of the destination station for the service")
    private String destinationName;
    /**
     * information source e.g. 'Network Rail' or 'ATOC'
     *
     */
    @JsonProperty("source")
    @JsonPropertyDescription("information source e.g. 'Network Rail' or 'ATOC'")
    private Source source;
    /**
     * The status of the service based on live data. If using Darwin data is enabled via darwin=true and the service appears as cancelled in Darwin, then this field would have a value of CANCELLED. A value of NO REPORT close to the departure time may indicate that the train starts at this location.
     * (Required)
     *
     */
    @JsonProperty("status")
    @JsonPropertyDescription("The status of the service based on live data. If using Darwin data is enabled via darwin=true and the service appears as cancelled in Darwin, then this field would have a value of CANCELLED. A value of NO REPORT close to the departure time may indicate that the train starts at this location.")
    private Status status;
    /**
     * Expected arrival time of the service. Based on live data if available, or timetable data if not. Can be in the past.
     * (Required)
     *
     */
    @JsonProperty("expected_arrival_time")
    @JsonPropertyDescription("Expected arrival time of the service. Based on live data if available, or timetable data if not. Can be in the past.")
    private String expectedArrivalTime;
    /**
     * Expected departure time of the service. Based on live data if available, or timetable data if not. Can be in the past.
     * (Required)
     *
     */
    @JsonProperty("expected_departure_time")
    @JsonPropertyDescription("Expected departure time of the service. Based on live data if available, or timetable data if not. Can be in the past.")
    private String expectedDepartureTime;
    /**
     * Estimated time offset in minutes until arrival of the service. Based on live data if available, or timetable data if not. Can be negative.
     * (Required)
     *
     */
    @JsonProperty("best_arrival_estimate_mins")
    @JsonPropertyDescription("Estimated time offset in minutes until arrival of the service. Based on live data if available, or timetable data if not. Can be negative.")
    private Integer bestArrivalEstimateMins;
    /**
     * Estimated time offset in minutes until departure of the service. Based on live data if available, or timetable data if not. Can be negative.
     * (Required)
     *
     */
    @JsonProperty("best_departure_estimate_mins")
    @JsonPropertyDescription("Estimated time offset in minutes until departure of the service. Based on live data if available, or timetable data if not. Can be negative.")
    private Integer bestDepartureEstimateMins;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("service_timetable")
    public Object getServiceTimetable() {
        return serviceTimetable;
    }

    @JsonProperty("service_timetable")
    public void setServiceTimetable(Object serviceTimetable) {
        this.serviceTimetable = serviceTimetable;
    }

    /**
     * The mode of the service: 'train' for regular services and 'bus' for replacement bus services.
     *
     */
    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    /**
     * The mode of the service: 'train' for regular services and 'bus' for replacement bus services.
     *
     */
    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * The category of the service: please refer to [full list](http://nrodwiki.rockshore.net/index.php/CIF_Codes#Train_Category) of possible values.
     *
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * The category of the service: please refer to [full list](http://nrodwiki.rockshore.net/index.php/CIF_Codes#Train_Category) of possible values.
     *
     */
    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * The service code of the service.
     *
     */
    @JsonProperty("service")
    public String getService() {
        return service;
    }

    /**
     * The service code of the service.
     *
     */
    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    /**
     * The train uid of the service.
     *
     */
    @JsonProperty("train_uid")
    public String getTrainUid() {
        return trainUid;
    }

    /**
     * The train uid of the service.
     *
     */
    @JsonProperty("train_uid")
    public void setTrainUid(String trainUid) {
        this.trainUid = trainUid;
    }

    /**
     * The platform at which the service would call at or pass through, if available.
     *
     */
    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    /**
     * The platform at which the service would call at or pass through, if available.
     *
     */
    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * The ATOC code of the train operating company operating the service.
     *
     */
    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    /**
     * The ATOC code of the train operating company operating the service.
     *
     */
    @JsonProperty("operator")
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * The aimed departure time of the service in hh:mm format according to the timetables. null if the service doesn't depart from the station of interest e.g. passing services.
     *
     */
    @JsonProperty("aimed_departure_time")
    public String getAimedDepartureTime() {
        return aimedDepartureTime;
    }

    /**
     * The aimed departure time of the service in hh:mm format according to the timetables. null if the service doesn't depart from the station of interest e.g. passing services.
     *
     */
    @JsonProperty("aimed_departure_time")
    public void setAimedDepartureTime(String aimedDepartureTime) {
        this.aimedDepartureTime = aimedDepartureTime;
    }

    /**
     * The aimed arrival time of the service in hh:mm format according to the timetables. null if the service doesn't arrive at the station of interest e.g. passing services.
     *
     */
    @JsonProperty("aimed_arrival_time")
    public String getAimedArrivalTime() {
        return aimedArrivalTime;
    }

    /**
     * The aimed arrival time of the service in hh:mm format according to the timetables. null if the service doesn't arrive at the station of interest e.g. passing services.
     *
     */
    @JsonProperty("aimed_arrival_time")
    public void setAimedArrivalTime(String aimedArrivalTime) {
        this.aimedArrivalTime = aimedArrivalTime;
    }

    /**
     * The aimed pass time of the service in hh:mm format according to the timetables. null if the service doesn't pass through the station of interest, e.g. arrivals and railDetailsHolder.
     *
     */
    @JsonProperty("aimed_pass_time")
    public String getAimedPassTime() {
        return aimedPassTime;
    }

    /**
     * The aimed pass time of the service in hh:mm format according to the timetables. null if the service doesn't pass through the station of interest, e.g. arrivals and railDetailsHolder.
     *
     */
    @JsonProperty("aimed_pass_time")
    public void setAimedPassTime(String aimedPassTime) {
        this.aimedPassTime = aimedPassTime;
    }

    /**
     * The name of the origin station for the service
     *
     */
    @JsonProperty("origin_name")
    public String getOriginName() {
        return originName;
    }

    /**
     * The name of the origin station for the service
     *
     */
    @JsonProperty("origin_name")
    public void setOriginName(String originName) {
        this.originName = originName;
    }

    /**
     * The name of the destination station for the service
     *
     */
    @JsonProperty("destination_name")
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * The name of the destination station for the service
     *
     */
    @JsonProperty("destination_name")
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * information source e.g. 'Network Rail' or 'ATOC'
     *
     */
    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    /**
     * information source e.g. 'Network Rail' or 'ATOC'
     *
     */
    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * The status of the service based on live data. If using Darwin data is enabled via darwin=true and the service appears as cancelled in Darwin, then this field would have a value of CANCELLED. A value of NO REPORT close to the departure time may indicate that the train starts at this location.
     * (Required)
     *
     */
    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    /**
     * The status of the service based on live data. If using Darwin data is enabled via darwin=true and the service appears as cancelled in Darwin, then this field would have a value of CANCELLED. A value of NO REPORT close to the departure time may indicate that the train starts at this location.
     * (Required)
     *
     */
    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Expected arrival time of the service. Based on live data if available, or timetable data if not. Can be in the past.
     * (Required)
     *
     */
    @JsonProperty("expected_arrival_time")
    public String getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    /**
     * Expected arrival time of the service. Based on live data if available, or timetable data if not. Can be in the past.
     * (Required)
     *
     */
    @JsonProperty("expected_arrival_time")
    public void setExpectedArrivalTime(String expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    /**
     * Expected departure time of the service. Based on live data if available, or timetable data if not. Can be in the past.
     * (Required)
     *
     */
    @JsonProperty("expected_departure_time")
    public String getExpectedDepartureTime() {
        return expectedDepartureTime;
    }

    /**
     * Expected departure time of the service. Based on live data if available, or timetable data if not. Can be in the past.
     * (Required)
     *
     */
    @JsonProperty("expected_departure_time")
    public void setExpectedDepartureTime(String expectedDepartureTime) {
        this.expectedDepartureTime = expectedDepartureTime;
    }

    /**
     * Estimated time offset in minutes until arrival of the service. Based on live data if available, or timetable data if not. Can be negative.
     * (Required)
     *
     */
    @JsonProperty("best_arrival_estimate_mins")
    public Integer getBestArrivalEstimateMins() {
        return bestArrivalEstimateMins;
    }

    /**
     * Estimated time offset in minutes until arrival of the service. Based on live data if available, or timetable data if not. Can be negative.
     * (Required)
     *
     */
    @JsonProperty("best_arrival_estimate_mins")
    public void setBestArrivalEstimateMins(Integer bestArrivalEstimateMins) {
        this.bestArrivalEstimateMins = bestArrivalEstimateMins;
    }

    /**
     * Estimated time offset in minutes until departure of the service. Based on live data if available, or timetable data if not. Can be negative.
     * (Required)
     *
     */
    @JsonProperty("best_departure_estimate_mins")
    public Integer getBestDepartureEstimateMins() {
        return bestDepartureEstimateMins;
    }

    /**
     * Estimated time offset in minutes until departure of the service. Based on live data if available, or timetable data if not. Can be negative.
     * (Required)
     *
     */
    @JsonProperty("best_departure_estimate_mins")
    public void setBestDepartureEstimateMins(Integer bestDepartureEstimateMins) {
        this.bestDepartureEstimateMins = bestDepartureEstimateMins;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public enum Source {

        NETWORK_RAIL("Network Rail"),
        ATOC("ATOC");
        private final String value;
        private final static Map<String, Source> CONSTANTS = new HashMap<String, Source>();

        static {
            for (Source c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Source(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Source fromValue(String value) {
            Source constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
