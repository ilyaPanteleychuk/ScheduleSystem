package com.ilyapanteleychuk.universityschedulebootsystem.service;

import com.ilyapanteleychuk.universityschedulebootsystem.entity.Group;
import java.util.List;

public interface GroupService {
    
    List<Group> loadGroupsByFacultyId(long facultyId);
}
