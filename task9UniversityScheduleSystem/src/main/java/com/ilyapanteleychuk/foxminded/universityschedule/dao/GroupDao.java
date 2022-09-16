package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import java.util.List;


public interface GroupDao {
    
    List<Group> loadGroupsByFacultyId(long facultyId);
    
}
