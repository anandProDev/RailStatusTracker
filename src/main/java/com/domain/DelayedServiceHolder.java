package com.domain;


import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

public class DelayedServiceHolder {

    @Id
    String date;

    Map<String, List<DelayedService>> delayedServices;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, List<DelayedService>> getDelayedServices() {
        return delayedServices;
    }

    public void setDelayedServices(Map<String, List<DelayedService>> delayedServices) {
        this.delayedServices = delayedServices;
    }
}
