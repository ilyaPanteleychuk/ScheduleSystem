package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.GroupLessonService;
import com.ilyapanteleychuk.foxminded.universityschedule.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ScheduleFormatterService {
    
    private final GroupLessonService groupLessonService;
    
    @Autowired
    public ScheduleFormatterService(GroupLessonService groupLessonService) {
        this.groupLessonService = groupLessonService;
    }
    
    public Map<String, List<GroupLesson>> formatLessons(long groupId){
        List<LocalDate> currentWeek = DateFormatter.getCurrentWeek();
        Map<String, List<GroupLesson>> lessons = new HashMap<>();
        List<GroupLesson> groupLessons = groupLessonService.loadLessonsPerWeek(groupId);
        lessons.put("Monday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(0))).collect(Collectors.toList()));
        lessons.put("Tuesday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(1))).collect(Collectors.toList()));
        lessons.put("Wednesday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(2))).collect(Collectors.toList()));
        lessons.put("Thursday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(3))).collect(Collectors.toList()));
        lessons.put("Friday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(4))).collect(Collectors.toList()));
        lessons.put("Saturday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(5))).collect(Collectors.toList()));
        lessons.put("Sunday", groupLessons.stream().filter(el -> el.getDate()
                .equals(currentWeek.get(6))).collect(Collectors.toList()));
        return lessons;
    }
}
