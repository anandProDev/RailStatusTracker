package com.service;


import com.domain.RailStatus;

public interface DelayTrackerService {

    void processDelays(RailStatus railStatus);

}
