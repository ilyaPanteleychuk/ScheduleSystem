package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface GroupLessonRepository extends JpaRepository<GroupLesson, Long> {
    
    List<GroupLesson> findAllByDateBetweenAndId(LocalDate start,
                                                LocalDate end, long groupId);
}
