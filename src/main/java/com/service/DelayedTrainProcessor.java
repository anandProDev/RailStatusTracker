package com.service;

import com.domain.RailDetail;
import com.domain.RailStatus;
import org.springframework.stereotype.Component;

@Component
public class DelayedTrainProcessor implements TrainStatusProcessor {


    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {

    }
}
