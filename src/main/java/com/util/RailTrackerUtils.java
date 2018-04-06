package com.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RailTrackerUtils {

    public static String getTodaysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }
}
