package com.service;

import com.model.RailDetail;
import com.model.RailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("DelayedTrainProcessor")
public class DelayedTrainProcessor implements TrainStatusProcessor {

    @Autowired
    DelayDurationCalculator delayDurationCalculator;

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {
        String delay = delayDurationCalculator.calculateDelay(railDetail);


    }
}
