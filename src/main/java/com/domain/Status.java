package com.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Status {

    ARRIVED("ARRIVED"),
    CANCELLED("CANCELLED"),
    CHANGE_OF_IDENTITY("CHANGE OF IDENTITY"),
    CHANGE_OF_ORIGIN("CHANGE OF ORIGIN"),
    EARLY("EARLY"),
    LATE("LATE"),
    NO_REPORT("NO REPORT"),
    OFF_ROUTE("OFF ROUTE"),
    ON_TIME("ON TIME"),
    REINSTATEMENT("REINSTATEMENT"),
    STARTS_HERE("STARTS HERE");
    private final String value;
    private final static Map<String, Status> CONSTANTS = new HashMap<String, Status>();

    static {
        for (Status c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private Status(String value) {
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
    public static Status fromValue(String value) {
        Status constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}