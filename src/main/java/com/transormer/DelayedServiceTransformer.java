package com.transormer;


import com.domain.DelayedService;
import com.domain.DelayedServiceHolder;
import com.model.RailDetail;
import com.model.RailStatus;
import org.springframework.stereotype.Component;
import com.util.RailTrackerUtils;

import java.util.*;

@Component
public class DelayedServiceTransformer {

    public DelayedServiceHolder transform(RailStatus railStatus, RailDetail railDetail, Optional<String> calculateDelay) {

        DelayedServiceHolder holder = new DelayedServiceHolder();

        holder.setDate(RailTrackerUtils.getTodaysDate());

        Map<String, List<DelayedService>> delayedServiceDetails = buildDelayedServiceDetails(railStatus, railDetail, calculateDelay);

        holder.setDelayedServices(delayedServiceDetails);

        return holder;
    }

    private Map<String, List<DelayedService>> buildDelayedServiceDetails(RailStatus railStatus, RailDetail railDetail, Optional<String> calculateDelay){

        Map<String, List<DelayedService>> details = new HashMap<>();

        List<DelayedService> delayedServices = new ArrayList<>();

        delayedServices.add(build(railDetail, calculateDelay));

        details.put(buildRailDetailKey(railDetail), delayedServices);

        return details;
    }

    private String buildRailDetailKey(RailDetail railDetail) {

        return railDetail.getTrainUid();
    }

    private DelayedService build(RailDetail railDetail, Optional<String> calculateDelay){

        return DelayedService.DelayedServiceBuilder.aDelayedService()
                .withTrainNumber(buildRailDetailKey(railDetail))
                .withAimedArrivalTime(railDetail.getAimedArrivalTime())
                .withAimedDepartureTime(railDetail.getAimedDepartureTime())
                .withDestination(railDetail.getDestinationName())
                .withSource(railDetail.getOriginName())
                .withRailCompany(railDetail.getSource().name())
                .withExpectedArrivalTime(railDetail.getExpectedArrivalTime())
                .withExpectedDepartureTime(railDetail.getExpectedDepartureTime())
                .withLengthOfDelay(calculateDelay.get())
                .withStatus(railDetail.getStatus().name())
                .withLastUpdatedTime(String.valueOf(Calendar.getInstance().getTime()))
                .build();
    }
}
