package com.service;


import com.model.RailDetail;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class DelayDurationCalculator {


    public Optional<String> calculateDelay(RailDetail railDetail){

        long aimedArrivalTime = getTimeInMillis(railDetail.getAimedArrivalTime());
        long expectedArrivalTime = getTimeInMillis(railDetail.getExpectedArrivalTime());

        long expectedDepartureTime = getTimeInMillis(railDetail.getExpectedDepartureTime());
        long aimedDepartureTime = getTimeInMillis(railDetail.getAimedDepartureTime());


        long arrivalTimeDiff = TimeUnit.MILLISECONDS.toMinutes(expectedArrivalTime - aimedArrivalTime);


        long departureDiff = TimeUnit.MILLISECONDS.toMinutes(expectedDepartureTime - aimedDepartureTime);

        if(arrivalTimeDiff > 30 || departureDiff > 30){
            System.out.println(railDetail.toString());
            return Optional.of(String.valueOf(departureDiff));
        }
        return Optional.empty();
    }


    private long getTimeInMillis(String hourMinute){

        List<String> hourMinuteList = Arrays.asList(hourMinute.split(":"));

        return DateTime.now().withHourOfDay(Integer.valueOf(hourMinuteList.get(0)))
                .withMinuteOfHour(Integer.valueOf(hourMinuteList.get(1))).getMillis();

    }

}
