package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Lesson;

import java.util.List;
import java.util.Map;


public interface LessonService <T extends Lesson> {
    
    Map<String, List<T>> loadLessonsPerWeek(long id);
}
