package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.TeacherLessonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class TeacherLessonServiceImpl
        implements CommonService<TeacherLesson>, LessonService<TeacherLesson> {
    
    private final ScheduleFormatterService<TeacherLesson> scheduleFormatterService;
    private final TeacherLessonDao teacherLessonDao;
    
    @Autowired
    public TeacherLessonServiceImpl(ScheduleFormatterService<TeacherLesson> scheduleFormatterService,
                                    TeacherLessonDao teacherLessonDao) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.teacherLessonDao = teacherLessonDao;
    }
    
    @Override
    public Map<String,List<TeacherLesson>> loadLessonsPerWeek(long id) {
        List<TeacherLesson> lessons = teacherLessonDao.loadLessonsPerWeek(id);
        return scheduleFormatterService.formatLessons(lessons);
    }
    
    @Override
    public void add(TeacherLesson teacherLesson) {
        teacherLessonDao.add(teacherLesson);
    }
    
    @Override
    public void addAll(List<TeacherLesson> teacherLessons) {
        teacherLessonDao.addAll(teacherLessons);
    }
    
    @Override
    public TeacherLesson load(TeacherLesson teacherLesson) {
        return teacherLessonDao.load(teacherLesson);
    }
    
    @Override
    public TeacherLesson loadById(long id) {
        return teacherLessonDao.loadById(id);
    }
    
    @Override
    public List<TeacherLesson> loadAll() {
        return teacherLessonDao.loadAll();
    }
    
    @Override
    public void update(long id, TeacherLesson teacherLesson) {
        teacherLessonDao.update(id, teacherLesson);
    }
    
    @Override
    public void delete(TeacherLesson teacherLesson) {
        teacherLessonDao.delete(teacherLesson);
    }
    
    @Override
    public void deleteById(long id) {
        teacherLessonDao.deleteById(id);
    }
}
