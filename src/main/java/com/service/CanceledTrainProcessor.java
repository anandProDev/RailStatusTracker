package com.service;


import com.model.RailDetail;
import com.model.RailStatus;
import org.springframework.stereotype.Component;

@Component("CanceledTrainProcessor")
public class CanceledTrainProcessor implements TrainStatusProcessor{

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {
        System.out.println("CanceledTrainProcessor");
    }
}
