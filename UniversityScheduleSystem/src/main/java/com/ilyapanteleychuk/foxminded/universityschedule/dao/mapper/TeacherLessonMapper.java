package com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.*;
import com.ilyapanteleychuk.universityschedule.entity.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherLessonMapper implements RowMapper<TeacherLesson> {
    
    @Override
    public TeacherLesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeacherLesson teacherLesson = new TeacherLesson();
        Audience audience = new Audience(rs.getInt("audience_number"));
        Subject subject = new Subject(rs.getString("title"));
        Faculty faculty = new Faculty("faculty_id");
        Group group = new Group(rs.getInt("groupnumber"), faculty);
        teacherLesson.setAudience(audience);
        teacherLesson.setSubject(subject);
        teacherLesson.setDate(rs.getDate("date").toLocalDate());
        teacherLesson.setType(rs.getString("type"));
        teacherLesson.setGroup(group);
        teacherLesson.setOrder(rs.getInt("lesson_order"));
        return teacherLesson;
    }
}
