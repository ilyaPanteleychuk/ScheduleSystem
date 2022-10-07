package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}