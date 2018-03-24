package com.db;


import com.domain.DelayedServiceHolder;
import com.model.RailDetailsHolder;

public interface PersistanceService {

    void updateRailDetails(DelayedServiceHolder delayedServiceHolder);

}
