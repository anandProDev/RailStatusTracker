package com.service;


import com.model.RailDetail;
import com.model.RailStatus;

public interface TrainStatusProcessor {

    void processTrains(RailStatus railStatus, RailDetail railDetail);
}
