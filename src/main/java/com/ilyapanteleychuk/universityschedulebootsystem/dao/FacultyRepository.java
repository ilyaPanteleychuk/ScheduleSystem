package com.ilyapanteleychuk.universityschedulebootsystem.dao;

import com.ilyapanteleychuk.universityschedulebootsystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}