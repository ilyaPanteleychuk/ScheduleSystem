package com.ilyapanteleychuk.foxminded.universityschedule.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DateFormatter {
    
    public static List<LocalDate> getCurrentWeek(){
        Calendar now = Calendar.getInstance();
        List<LocalDate> days = new ArrayList<>();
        int delta = -now.get(Calendar.DAY_OF_WEEK) + 2;
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            LocalDate localDate = now.getTime().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            days.add(localDate);
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }
}
