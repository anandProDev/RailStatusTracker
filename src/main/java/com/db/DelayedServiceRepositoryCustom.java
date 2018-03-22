package com.db;


import com.domain.DelayedServiceHolder;
import com.model.RailDetailsHolder;

public interface DelayedServiceRepositoryCustom {

    int updateRailDetails(DelayedServiceHolder delayedServiceHolder);

}
