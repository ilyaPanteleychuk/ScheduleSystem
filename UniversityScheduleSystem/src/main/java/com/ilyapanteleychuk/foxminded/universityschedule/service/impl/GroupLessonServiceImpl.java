package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.GroupLessonRepository;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
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
public class GroupLessonServiceImpl implements
        CommonService<GroupLesson>, LessonService<GroupLesson> {
    
    private final ScheduleFormatterService<GroupLesson> scheduleFormatterService;
    private final GroupLessonRepository groupLessonRepository;
    
    @Autowired
    public GroupLessonServiceImpl(ScheduleFormatterService<GroupLesson>
                                          scheduleFormatterService,
                                  GroupLessonRepository groupLessonRepository) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.groupLessonRepository = groupLessonRepository;
    }
    
    @Override
    @Transactional
    public Map<String,List<GroupLesson>> loadLessonsPerWeek(long groupId) {
        LocalDate start = LocalDate.now(ZoneId.systemDefault());
        LocalDate end = start.plusDays(7);
        List<GroupLesson> lessons = groupLessonRepository
                .findAllByDateBetweenAndGroupId(start, end, groupId);
        return scheduleFormatterService.formatLessons(lessons);
    }
    
    @Override
    @Transactional
    public void save(GroupLesson groupLesson) {
        groupLessonRepository.save(groupLesson);
    }
    
    @Override
    @Transactional
    public void saveAll(List<GroupLesson> groupLessons) {
        groupLessonRepository.saveAll(groupLessons);
    }
    
    @Override
    @Transactional
    public GroupLesson findById(long id) {
        Optional<GroupLesson> optionalLesson = groupLessonRepository.findById(id);
        return optionalLesson.orElse(null);
    }
    
    @Override
    @Transactional
    public List<GroupLesson> findAll() {
        return groupLessonRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(GroupLesson groupLesson) {
        groupLessonRepository.save(groupLesson);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        groupLessonRepository.deleteById(id);
    }
}
