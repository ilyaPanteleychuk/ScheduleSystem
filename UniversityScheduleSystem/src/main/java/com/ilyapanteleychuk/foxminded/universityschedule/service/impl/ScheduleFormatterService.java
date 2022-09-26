package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Lesson;
import com.ilyapanteleychuk.foxminded.universityschedule.utils.DateFormatter;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ScheduleFormatterService <T extends Lesson> {
    
    public Map<String, List<T>> formatLessons(List<T> lessons){
        List<LocalDate> currentWeek = DateFormatter.getCurrentWeek();
        Map<String, List<T>> sortedLessons = new HashMap<>();
        sortedLessons.put("Monday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(0))).collect(Collectors.toList()));
        sortedLessons.put("Tuesday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(1))).collect(Collectors.toList()));
        sortedLessons.put("Wednesday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(2))).collect(Collectors.toList()));
        sortedLessons.put("Thursday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(3))).collect(Collectors.toList()));
        sortedLessons.put("Friday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(4))).collect(Collectors.toList()));
        sortedLessons.put("Saturday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(5))).collect(Collectors.toList()));
        sortedLessons.put("Sunday", lessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(6))).collect(Collectors.toList()));
        return sortedLessons;
    }
}
