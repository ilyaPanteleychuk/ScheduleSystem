package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupLessonDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.GroupLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GroupLessonServiceImpl implements CommonService<GroupLesson>, GroupLessonService {
    
    private final GroupLessonDaoImpl groupLessonDaoImpl;
    private final FacultyService facultyService;
    private final GroupServiceImpl groupServiceImpl;
    
    @Autowired
    public GroupLessonServiceImpl(GroupLessonDaoImpl groupLessonDaoImpl,
                                  FacultyService facultyService, GroupServiceImpl groupServiceImpl) {
        this.groupLessonDaoImpl = groupLessonDaoImpl;
        this.facultyService = facultyService;
        this.groupServiceImpl = groupServiceImpl;
    }
    
    @Override
    public List<GroupLesson> loadLessonsPerWeek(long groupId) {
        return groupLessonDaoImpl.loadLessonsPerWeek(groupId);
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
