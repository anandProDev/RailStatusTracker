package com.domain;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Map<String, Object>> toMap(){
        List<Map<String, Object>> holder = new ArrayList<>();
        delayedServices.forEach((k,v) -> buildDelayedList(holder, k, v));

        return holder;
    }

    @Override
    public String toString() {
        return "DelayedServiceHolder{" +
                "date='" + date + '\'' +
                ", delayedServices=" + delayedServices +
                '}';
    }

    private void buildDelayedList(List<Map<String, Object>> holder, String documentDate, List<DelayedService> delayedServices) {

        List<Map<String, Object>> delayedList = new ArrayList<>();

        delayedServices.forEach(service ->  {

            HashMap<String, Object> s = new HashMap<>();

                s.put("date", documentDate);
                s.put("trainId", service.getTrainNumber());
                s.put("source", service.getSource());
                s.put("destination", service.getDestination());
                s.put("lengthOfDelay", service.getLengthOfDelay());
                s.put("status", service.getStatus());
                s.put("expectedDepartureTime", service.getExpectedDepartureTime());
                s.put("expectedArrivalTime", service.getExpectedArrivalTime());
                s.put("railCompany", service.getRailCompany());
                s.put("aimedDepartureTime", service.getAimedDepartureTime());
                s.put("aimedArrivalTime", service.getAimedArrivalTime());
                s.put("lastUpdatedTime", service.getLastUpdatedTime());

                delayedList.add(s);
            }
        );

        holder.addAll(delayedList);
    }
}
