package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupLessonDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class GroupLessonServiceImpl implements
        CommonService<GroupLesson>, LessonService<GroupLesson> {
    
    private final ScheduleFormatterService<GroupLesson> scheduleFormatterService;
    private final GroupLessonDaoImpl groupLessonDaoImpl;
    
    @Autowired
    public GroupLessonServiceImpl(ScheduleFormatterService<GroupLesson> scheduleFormatterService,
                                  GroupLessonDaoImpl groupLessonDaoImpl) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.groupLessonDaoImpl = groupLessonDaoImpl;
    }
    
    @Override
    public Map<String,List<GroupLesson>> loadLessonsPerWeek(long id) {
        List<GroupLesson> lessons = groupLessonDaoImpl.loadLessonsPerWeek(id);
        return scheduleFormatterService.formatLessons(lessons);
    }
    
    @Override
    public void add(GroupLesson groupLesson) {
        groupLessonDaoImpl.add(groupLesson);
    }
    
    @Override
    public void addAll(List<GroupLesson> groupLessons) {
        groupLessonDaoImpl.addAll(groupLessons);
    }
    
    @Override
    public GroupLesson load(GroupLesson groupLesson) {
        return groupLessonDaoImpl.load(groupLesson);
    }
    
    @Override
    public GroupLesson loadById(long id) {
        return groupLessonDaoImpl.loadById(id);
    }
    
    @Override
    public List<GroupLesson> loadAll() {
        return groupLessonDaoImpl.loadAll();
    }
    
    @Override
    public void update(long id, GroupLesson groupLesson) {
        groupLessonDaoImpl.update(id, groupLesson);
    }
    
    @Override
    public void delete(GroupLesson groupLesson) {
        groupLessonDaoImpl.delete(groupLesson);
    }
    
    @Override
    public void deleteById(long id) {
        groupLessonDaoImpl.deleteById(id);
    }
    
}
