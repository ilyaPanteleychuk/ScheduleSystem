package com.ilyapanteleychuk.universityschedulebootsystem.dao;

import com.ilyapanteleychuk.universityschedulebootsystem.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {
    
    List<Group> findAllByFaculty_Id(long faculty_id);
}
