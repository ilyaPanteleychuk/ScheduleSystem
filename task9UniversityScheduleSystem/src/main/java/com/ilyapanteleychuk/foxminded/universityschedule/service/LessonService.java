package com.ilyapanteleychuk.foxminded.universityschedule.service;


import com.ilyapanteleychuk.foxminded.universityschedule.entity.Lesson;
import java.util.List;


public interface LessonService <T extends Lesson> {
    
    List<T> loadByUserIdPerTimePeriod(long id, int timePeriod);
    
}
