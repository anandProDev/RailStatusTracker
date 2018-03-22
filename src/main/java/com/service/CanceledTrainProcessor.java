package com.service;


import com.db.DelayedServiceRepositoryCustom;
import com.domain.DelayedService;
import com.domain.DelayedServiceHolder;
import com.model.RailDetail;
import com.model.RailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CANCELLED")
public class CanceledTrainProcessor implements TrainStatusProcessor{

    @Autowired
    DelayedServiceRepositoryCustom delayedServiceRepositoryCustom;

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {

        DelayedServiceHolder delayedServiceHolder = transform(railStatus, railDetail);
        delayedServiceRepositoryCustom.updateRailDetails(delayedServiceHolder);
    }

    private DelayedServiceHolder transform(RailStatus railStatus, RailDetail railDetail) {

        DelayedServiceHolder holder = new DelayedServiceHolder();

        Map<String, List<DelayedService>> delayedServiceDetails = buildDelayedServiceDetails(railStatus, railDetail);

        holder.setDelayedServices(delayedServiceDetails);

        return holder;
    }


    private Map<String, List<DelayedService>> buildDelayedServiceDetails(RailStatus railStatus, RailDetail railDetail){

        Map<String, List<DelayedService>> details = new HashMap<String, List<DelayedService>>();


        List<DelayedService> delayedServices = new ArrayList<>();


        delayedServices.add(new DelayedService(railDetail.getTrainUid(), railDetail.getOriginName(),
                railDetail.getDestinationName(), "Cancelled", railDetail.getStatus().name()));

        details.put(getTodaysDate(), delayedServices);

        return details;
    }

    private String getTodaysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

}
