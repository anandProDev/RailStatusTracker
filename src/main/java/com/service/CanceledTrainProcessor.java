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

@Component("CANCELLED")
public class CanceledTrainProcessor implements TrainStatusProcessor{


    private final DelayedServiceTransformer delayedServiceTransformer;
    private final String databaseName;
    private final Map<String, PersistanceService> persistanceServiceMap;

    @Autowired
    public CanceledTrainProcessor(Map<String, PersistanceService> persistanceServiceMap,
                                  DelayedServiceTransformer delayedServiceTransformer,
                                  @Value("${application.databaseName.name}") String databaseName) {
        this.persistanceServiceMap = persistanceServiceMap;
        this.delayedServiceTransformer = delayedServiceTransformer;
        this.databaseName = databaseName;
    }

    @Override
    public void processTrains(RailStatus railStatus, RailDetail railDetail) {

        Optional<String> calculateDelay = Optional.of("Cancelled: 2:00");
        DelayedServiceHolder delayedServiceHolder = delayedServiceTransformer.transform(railStatus, railDetail, calculateDelay);

        System.out.println(delayedServiceHolder.toString());

        persistanceServiceMap.get(databaseName).updateRailDetails(delayedServiceHolder);
    }
}
