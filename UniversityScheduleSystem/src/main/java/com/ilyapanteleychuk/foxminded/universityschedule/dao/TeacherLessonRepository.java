package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;


public interface TeacherLessonRepository extends JpaRepository<TeacherLesson, Long> {
    
    @Query(value = " SELECT * FROM university.lesson tl " +
            "WHERE tl.date BETWEEN :start AND :end AND tl.teacher.id=:teacherId",
            nativeQuery = true)
    List<TeacherLesson> findAllByDateBetweenAndTeacherId(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("teacherId") long teacherId
    );
}