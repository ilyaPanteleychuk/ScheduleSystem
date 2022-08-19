package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.ScheduleDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ScheduleDaoImpl implements ScheduleDao {
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("schedule_id");
    
    @Autowired
    public ScheduleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(Schedule schedule) {
        final String insertSql = "INSERT INTO university.schedule VALUES(?)";
        jdbcTemplate.update(insertSql, schedule.getId());
    }
    
    @Override
    public Schedule getById(int id) {
        final String selectSql = "SELECT * FROM university.schedule WHERE schedule_id = ?";
        return jdbcTemplate.query(selectSql, new Object[]{id},
                        new BeanPropertyRowMapper<Schedule>())
                .stream().findAny().orElse(null);
    }
    
    public static List<String> getColumns(){
        return columns;
    }
}
