package com.db;


import com.domain.DelayedServiceHolder;
import org.jetbrains.annotations.NotNull;

public interface PersistanceService {

    void updateRailDetails(DelayedServiceHolder delayedServiceHolder);
    DelayedServiceHolder getDetails(@NotNull String date);
}
