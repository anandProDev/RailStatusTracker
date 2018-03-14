package com.service;


import com.model.RailStatus;

public interface DelayTrackerService {

    void processDelays(RailStatus railStatus);

}
