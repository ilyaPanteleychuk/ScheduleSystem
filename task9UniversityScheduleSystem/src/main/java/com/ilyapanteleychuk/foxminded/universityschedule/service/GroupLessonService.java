package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import java.util.List;


public interface GroupLessonService {
    
    List<GroupLesson> loadLessonsPerWeek(long groupId);
}
