package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import java.util.List;


public interface GroupLessonDao {
    
    List<GroupLesson> loadLessonsPerWeek(long groupId);
}
