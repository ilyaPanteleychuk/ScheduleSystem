package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Long> {
    
    List<Group> findAllByFaculty_Id(long faculty_id);
}
