package com.db;


import com.domain.DelayedServiceHolder;
import com.model.RailDetailsHolder;
import org.jetbrains.annotations.NotNull;

public interface PersistanceService {

    void updateRailDetails(DelayedServiceHolder delayedServiceHolder);
    DelayedServiceHolder getDetails(@NotNull String date);
}
