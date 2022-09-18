package com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GroupMapper implements RowMapper<Group> {
    
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("id_group"));
        group.setGroupNumber(rs.getInt("groupnumber"));
        Faculty faculty = new Faculty();
        faculty.setId(rs.getInt("faculty_id"));
        faculty.setTitle(rs.getString("title_faculty"));
        group.setFaculty(faculty);
        return group;
    }
}
