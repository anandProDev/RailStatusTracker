package com.service;

import com.domain.RailDetail;
import com.domain.RailStatus;
import com.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DelayTrackerServiceImpl implements DelayTrackerService {

    private final Map<String, TrainStatusProcessor> trainStatusProcessorMap;

    @Autowired
    public DelayTrackerServiceImpl(Map<String, TrainStatusProcessor> trainStatusProcessorMap) {
        this.trainStatusProcessorMap = trainStatusProcessorMap;
    }

    @Override
    public void processDelays(RailStatus railStatus) {

        List<RailDetail> railDetail = railStatus.getRailDetailsHolder().getRailDetail();

        railDetail.forEach(railDetail1 -> {

            if(Status.CANCELLED.name().equalsIgnoreCase(railDetail1.getStatus().name()))
                trainStatusProcessorMap.get("ca");

            else if(Status.LATE.name().equalsIgnoreCase(railDetail1.getStatus().name())){

            }

            else {

            }


        });

    }
}
