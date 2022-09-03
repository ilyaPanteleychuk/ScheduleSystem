package com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupSchedule;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GroupScheduleMapper implements RowMapper<GroupSchedule> {
    
    @Override
    public GroupSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupSchedule groupSchedule = new GroupSchedule();
        Group group = new Group(rs.getInt("groupnumber"));
        groupSchedule.setId(rs.getInt("id"));
        groupSchedule.setGroup(group);
        return groupSchedule;
    }
}
