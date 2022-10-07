package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;


public interface GroupLessonRepository extends JpaRepository<GroupLesson, Long> {
    
    @Query(value = " SELECT * FROM university.lesson tl " +
            "WHERE tl.date BETWEEN :start AND :end AND tl.group_id=:groupId",
            nativeQuery = true)
    List<GroupLesson> findAllByDateBetweenAndGroupId(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("groupId") long groupId
    );
}
