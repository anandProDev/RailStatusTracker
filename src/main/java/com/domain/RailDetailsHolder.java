package com.domain;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds a collection of the requested train railDetailsHolder.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "railDetail"
})
public class RailDetailsHolder {

    /**
     * A utility container for railDetail railDetailsHolder.
     * (Required)
     *
     */
    @JsonProperty("departures")
    @JsonPropertyDescription("A utility container for railDetail railDetailsHolder.")
    private List<RailDetail> railDetail = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * A utility container for railDetail railDetailsHolder.
     * (Required)
     *
     */
    @JsonProperty("railDetail")
    public List<RailDetail> getRailDetail() {
        return railDetail;
    }

    /**
     * A utility container for railDetail railDetailsHolder.
     * (Required)
     *
     */
    @JsonProperty("railDetail")
    public void setRailDetail(List<RailDetail> railDetail) {
        this.railDetail = railDetail;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
