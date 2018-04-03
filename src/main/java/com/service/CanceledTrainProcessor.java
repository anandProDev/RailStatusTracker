package com.service;


import com.db.PersistanceService;
import com.domain.DelayedServiceHolder;
import com.model.RailDetail;
import com.model.RailStatus;
import com.transormer.DelayedServiceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("CANCELLED")
public class CanceledTrainProcessor implements TrainStatusProcessor{


    private final PersistanceService persistanceService;
    private final DelayedServiceTransformer delayedServiceTransformer;

    @Autowired
    public CanceledTrainProcessor(PersistanceService persistanceService, DelayedServiceTransformer delayedServiceTransformer) {
        this.persistanceService = persistanceService;
        this.delayedServiceTransformer = delayedServiceTransformer;
    }

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {

        Optional<String> calculateDelay = Optional.of("Cancelled: 2:00");
        DelayedServiceHolder delayedServiceHolder = delayedServiceTransformer.transform(railStatus, railDetail, calculateDelay);
        persistanceService.updateRailDetails(delayedServiceHolder);
    }
}
