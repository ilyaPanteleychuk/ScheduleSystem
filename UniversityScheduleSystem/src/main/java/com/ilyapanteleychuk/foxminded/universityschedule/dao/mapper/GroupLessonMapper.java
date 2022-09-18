package com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GroupLessonMapper implements RowMapper<GroupLesson> {
    
    @Override
    public GroupLesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupLesson groupLesson = new GroupLesson();
        Audience audience = new Audience(rs.getInt("audience_number"));
        Subject subject = new Subject(rs.getString("title"));
        Teacher teacher = new Teacher(rs.getString("first_name"),
                rs.getString("last_name"));
        groupLesson.setAudience(audience);
        groupLesson.setSubject(subject);
        groupLesson.setDate(rs.getDate("date").toLocalDate());
        groupLesson.setType(rs.getString("type"));
        groupLesson.setTeacher(teacher);
        groupLesson.setOrder(rs.getInt("lesson_order"));
        return groupLesson;
    }
}
