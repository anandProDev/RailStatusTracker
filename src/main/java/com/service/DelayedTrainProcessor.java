package com.service;

import com.db.PersistanceService;
import com.domain.DelayedServiceHolder;
import com.model.RailDetail;
import com.model.RailStatus;
import com.transormer.DelayedServiceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component("LATE")
public class DelayedTrainProcessor implements TrainStatusProcessor {

    private final DelayDurationCalculator delayDurationCalculator;
    private final Map<String, PersistanceService> persistanceServiceMap;
    private final String databaseName;
    private final DelayedServiceTransformer delayedServiceTransformer;

    @Autowired
    public DelayedTrainProcessor(DelayDurationCalculator delayDurationCalculator,
                                 Map<String, PersistanceService> persistanceServiceMap,
                                 @Value("${application.databaseName.name}") String databaseName,
                                 DelayedServiceTransformer delayedServiceTransformer) {

        this.delayDurationCalculator = delayDurationCalculator;
        this.persistanceServiceMap = persistanceServiceMap;
        this.databaseName = databaseName;
        this.delayedServiceTransformer = delayedServiceTransformer;
    }

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {
        Optional<String> calculateDelay = delayDurationCalculator.calculateDelay(railDetail);

        if(!calculateDelay.isPresent())
            return;

        DelayedServiceHolder delayedServiceHolder = delayedServiceTransformer.transform(railStatus, railDetail, calculateDelay);

        persistanceServiceMap.get(databaseName).updateRailDetails(delayedServiceHolder);
    }
}
