package com.service;

import com.db.PersistanceService;
import com.domain.DelayedServiceHolder;
import com.model.RailDetail;
import com.model.RailStatus;
import com.transormer.DelayedServiceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("DelayedTrainProcessor")
public class DelayedTrainProcessor implements TrainStatusProcessor {

    private final DelayDurationCalculator delayDurationCalculator;
    private final PersistanceService persistanceService;
    private final DelayedServiceTransformer delayedServiceTransformer;

    @Autowired
    public DelayedTrainProcessor(DelayDurationCalculator delayDurationCalculator, PersistanceService persistanceService, DelayedServiceTransformer delayedServiceTransformer) {
        this.delayDurationCalculator = delayDurationCalculator;
        this.persistanceService = persistanceService;
        this.delayedServiceTransformer = delayedServiceTransformer;
    }

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {
        Optional<String> calculateDelay = delayDurationCalculator.calculateDelay(railDetail);

        if(!calculateDelay.isPresent())
            return;

        DelayedServiceHolder delayedServiceHolder = delayedServiceTransformer.transform(railStatus, railDetail);

        persistanceService.updateRailDetails(delayedServiceHolder);
    }
}
