package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import java.util.List;


public interface GroupService {
    
    List<Group> loadGroupsByFacultyId(long facultyId);
}
