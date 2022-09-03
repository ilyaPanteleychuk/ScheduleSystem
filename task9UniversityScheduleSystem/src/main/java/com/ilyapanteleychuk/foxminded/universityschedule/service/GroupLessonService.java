package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupLessonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GroupLessonService implements CommonService<GroupLesson>, LessonService<GroupLesson> {
    
    private final GroupLessonDao groupLessonDao;
    
    @Autowired
    public GroupLessonService(GroupLessonDao groupLessonDao) {
        this.groupLessonDao = groupLessonDao;
    }
    
    @Override
    public List<GroupLesson> loadByUserIdPerTimePeriod(long id, int timePeriod) {
        return groupLessonDao.loadByUserIdPerTimePeriod(id, timePeriod);
    }
    
    @Override
    public void add(GroupLesson groupLesson) {
        groupLessonDao.add(groupLesson);
    }
    
    @Override
    public void addAll(List<GroupLesson> groupLessons) {
        groupLessonDao.addAll(groupLessons);
    }
    
    @Override
    public GroupLesson load(GroupLesson groupLesson) {
        return groupLessonDao.load(groupLesson);
    }
    
    @Override
    public GroupLesson loadById(long id) {
        return groupLessonDao.loadById(id);
    }
    
    @Override
    public List<GroupLesson> loadAll() {
        return groupLessonDao.loadAll();
    }
    
    @Override
    public void update(long id, GroupLesson groupLesson) {
        groupLessonDao.update(id, groupLesson);
    }
    
    @Override
    public void delete(GroupLesson groupLesson) {
        groupLessonDao.delete(groupLesson);
    }
    
    @Override
    public void deleteById(long id) {
        groupLessonDao.deleteById(id);
    }
}
