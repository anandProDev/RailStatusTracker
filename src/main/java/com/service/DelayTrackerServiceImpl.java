package com.service;

import com.model.RailDetail;
import com.model.RailStatus;
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

          //  System.out.println("  "+ railDetail1.getOriginName() + " : "+ railDetail1.getDestinationName() + " Rails STATUS : " + railDetail1.getStatus().name());

            TrainStatusProcessor trainStatusProcessor = trainStatusProcessorMap.get(railDetail1.getStatus().name());
            if(trainStatusProcessor == null)
                return;


            trainStatusProcessor.processTrains(railStatus, railDetail1);
        });

    }
}
