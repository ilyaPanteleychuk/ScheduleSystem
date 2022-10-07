package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.TeacherLessonRepository;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class TeacherLessonServiceImpl
        implements CommonService<TeacherLesson>, LessonService<TeacherLesson> {
    
    private final ScheduleFormatterService<TeacherLesson> scheduleFormatterService;
    private final TeacherLessonRepository teacherLessonRepository;
    
    @Autowired
    public TeacherLessonServiceImpl(ScheduleFormatterService<TeacherLesson>
                                                scheduleFormatterService,
                                    TeacherLessonRepository teacherLessonRepository) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.teacherLessonRepository = teacherLessonRepository;
    }
    
    @Override
    @Transactional
    public Map<String,List<TeacherLesson>> loadLessonsPerWeek(long teacherId) {
        LocalDate start = LocalDate.now(ZoneId.systemDefault());
        LocalDate end = start.plusDays(7);
        List<TeacherLesson> lessons = teacherLessonRepository
                .findAllByDateBetweenAndTeacherId(start, end, teacherId);
        return scheduleFormatterService.formatLessons(lessons);
    }
    
    @Override
    @Transactional
    public void save(TeacherLesson teacherLesson) {
        teacherLessonRepository.save(teacherLesson);
    }
    
    @Override
    @Transactional
    public void saveAll(List<TeacherLesson> teacherLessons) {
        teacherLessonRepository.saveAll(teacherLessons);
    }
    
    @Override
    @Transactional
    public TeacherLesson findById(long id) {
        Optional<TeacherLesson> optionalLesson =
                teacherLessonRepository.findById(id);
        return optionalLesson.orElse(null);
    }
    
    @Override
    @Transactional
    public List<TeacherLesson> findAll() {
        return teacherLessonRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(TeacherLesson teacherLesson) {
        teacherLessonRepository.save(teacherLesson);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        teacherLessonRepository.deleteById(id);
    }
}
