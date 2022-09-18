package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Lesson;

import java.util.List;


public interface LessonDao<T extends Lesson> {
    
    List<T> loadLessonsPerWeek(long groupId);
}
