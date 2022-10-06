package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}