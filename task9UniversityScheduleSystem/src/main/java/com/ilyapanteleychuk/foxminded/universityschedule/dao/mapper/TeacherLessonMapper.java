package com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherLessonMapper implements RowMapper<TeacherLesson> {
    
    @Override
    public TeacherLesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeacherLesson teacherLesson = new TeacherLesson();
        Audience audience = new Audience(rs.getInt("audience_number"));
        Subject subject = new Subject(rs.getString("title"));
        Group group = new Group(rs.getInt("groupnumber"));
        Schedule schedule = new Schedule(rs.getInt("schedule_id"));
        teacherLesson.setAudience(audience);
        teacherLesson.setSubject(subject);
        teacherLesson.setDate(rs.getDate("lesson_date").toLocalDate());
        teacherLesson.setType(rs.getString("lesson_type"));
        teacherLesson.setGroup(group);
        teacherLesson.setOrder(rs.getInt("lesson_order"));
        teacherLesson.setSchedule(schedule);
        return teacherLesson;
    }
}
