package com.ilyapanteleychuk.universityschedulebootsystem.service;

import com.ilyapanteleychuk.universityschedulebootsystem.entity.Lesson;
import java.util.List;
import java.util.Map;


public interface LessonService <T extends Lesson> {
    
    Map<String, List<T>> loadLessonsPerWeek(long id);
}
