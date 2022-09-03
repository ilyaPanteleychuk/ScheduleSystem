package com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherSchedule;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherScheduleMapper implements RowMapper<TeacherSchedule> {
    
    @Override
    public TeacherSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeacherSchedule teacherSchedule = new TeacherSchedule();
        teacherSchedule.setId(rs.getInt("id"));
        Teacher teacher = new Teacher(rs.getString("first_name"),
                rs.getString("last_name"));
        teacherSchedule.setTeacher(teacher);
        return teacherSchedule;
    }
}
