package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Schedule;


public interface ScheduleDao {
    
    void add(Schedule schedule);
    
    Schedule getById(int id);
}
