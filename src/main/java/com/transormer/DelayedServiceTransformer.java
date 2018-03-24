package com.transormer;


import com.RailStatusTrackerApplication;
import com.domain.DelayedService;
import com.domain.DelayedServiceHolder;
import com.model.RailDetail;
import com.model.RailStatus;
import org.springframework.stereotype.Component;
import util.RailTrackerUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DelayedServiceTransformer {

    public DelayedServiceHolder transform(RailStatus railStatus, RailDetail railDetail) {

        DelayedServiceHolder holder = new DelayedServiceHolder();

        holder.setDate(RailTrackerUtils.getTodaysDate());

        Map<String, List<DelayedService>> delayedServiceDetails = buildDelayedServiceDetails(railStatus, railDetail);

        holder.setDelayedServices(delayedServiceDetails);

        return holder;
    }


    private Map<String, List<DelayedService>> buildDelayedServiceDetails(RailStatus railStatus, RailDetail railDetail){

        Map<String, List<DelayedService>> details = new HashMap<String, List<DelayedService>>();

        List<DelayedService> delayedServices = new ArrayList<>();

        delayedServices.add(new DelayedService(railDetail.getTrainUid(), railDetail.getOriginName(),
                railDetail.getDestinationName(), "Cancelled", railDetail.getStatus().name()));

        details.put(railDetail.getTrainUid(), delayedServices);

        return details;
    }
}
