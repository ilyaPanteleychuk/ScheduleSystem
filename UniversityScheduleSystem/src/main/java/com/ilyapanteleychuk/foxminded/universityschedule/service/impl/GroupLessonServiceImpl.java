package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupLessonDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;


@Service
public class GroupLessonServiceImpl implements
        CommonService<GroupLesson>, LessonService<GroupLesson> {
    
    private final ScheduleFormatterService<GroupLesson> scheduleFormatterService;
    private final GroupLessonDaoImpl groupLessonDaoImpl;
    
    @Autowired
    public GroupLessonServiceImpl(ScheduleFormatterService<GroupLesson>
                                              scheduleFormatterService,
                                  GroupLessonDaoImpl groupLessonDaoImpl) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.groupLessonDaoImpl = groupLessonDaoImpl;
    }
    
    @Override
    @Transactional
    public Map<String,List<GroupLesson>> loadLessonsPerWeek(long id) {
        List<GroupLesson> lessons = groupLessonDaoImpl.loadLessonsPerWeek(id);
        return scheduleFormatterService.formatLessons(lessons);
    }
    
    @Override
    @Transactional
    public void add(GroupLesson groupLesson) {
        groupLessonDaoImpl.save(groupLesson);
    }
    
    @Override
    @Transactional
    public void addAll(List<GroupLesson> groupLessons) {
        groupLessonDaoImpl.saveAll(groupLessons);
    }
    
    @Override
    @Transactional
    public GroupLesson loadById(long id) {
        return groupLessonDaoImpl.loadById(id);
    }
    
    @Override
    @Transactional
    public List<GroupLesson> loadAll() {
        return groupLessonDaoImpl.loadAll();
    }
    
    @Override
    @Transactional
    public void update(GroupLesson groupLesson) {
        groupLessonDaoImpl.update(groupLesson);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        groupLessonDaoImpl.deleteById(id);
    }
}
