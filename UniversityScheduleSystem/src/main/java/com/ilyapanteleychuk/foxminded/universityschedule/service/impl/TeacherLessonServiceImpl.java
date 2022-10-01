package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.TeacherLessonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;


@Service
public class TeacherLessonServiceImpl
        implements CommonService<TeacherLesson>, LessonService<TeacherLesson> {
    
    private final ScheduleFormatterService<TeacherLesson> scheduleFormatterService;
    private final TeacherLessonDao teacherLessonDao;
    
    @Autowired
    public TeacherLessonServiceImpl(ScheduleFormatterService<TeacherLesson>
                                                scheduleFormatterService,
                                    TeacherLessonDao teacherLessonDao) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.teacherLessonDao = teacherLessonDao;
    }
    
    @Override
    @Transactional
    public Map<String,List<TeacherLesson>> loadLessonsPerWeek(long id) {
        List<TeacherLesson> lessons = teacherLessonDao.loadLessonsPerWeek(id);
        return scheduleFormatterService.formatLessons(lessons);
    }
    
    @Override
    @Transactional
    public void add(TeacherLesson teacherLesson) {
        teacherLessonDao.save(teacherLesson);
    }
    
    @Override
    @Transactional
    public void addAll(List<TeacherLesson> teacherLessons) {
        teacherLessonDao.saveAll(teacherLessons);
    }
    
    @Override
    @Transactional
    public TeacherLesson loadById(long id) {
        return teacherLessonDao.loadById(id);
    }
    
    @Override
    @Transactional
    public List<TeacherLesson> loadAll() {
        return teacherLessonDao.loadAll();
    }
    
    @Override
    @Transactional
    public void update(TeacherLesson teacherLesson) {
        teacherLessonDao.update(teacherLesson);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        teacherLessonDao.deleteById(id);
    }
}
