package com.service;


import com.domain.RailDetail;
import com.domain.RailStatus;

public interface TrainStatusProcessor {

    void processTrains(RailStatus railStatus, RailDetail railDetail);
}
