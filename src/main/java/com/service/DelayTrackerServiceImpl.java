package com.service;

import com.model.RailDetail;
import com.model.RailStatus;
import com.model.Status;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
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

            if(Status.CANCELLED.name().equalsIgnoreCase(railDetail1.getStatus().name())){
                System.out.println("Rails STATUS : " + railDetail1.getStatus().name());
                trainStatusProcessorMap.get("CANCELLED").processTrains(railStatus, railDetail1);
            }

            else if(Status.LATE.name().equalsIgnoreCase(railDetail1.getStatus().name())){
                System.out.println("Rails STATUS : " + railDetail1.getStatus().name());
                trainStatusProcessorMap.get("DelayedTrainProcessor").processTrains(railStatus, railDetail1);
            }
            else {
                System.out.println("Rails on time : " + railDetail1.getStatus().name());
                trainStatusProcessorMap.get("CANCELLED").processTrains(railStatus, railDetail1);
            }
        });

    }
}
