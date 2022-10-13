package com.ilyapanteleychuk.universityschedulebootsystem.dao;

import com.ilyapanteleychuk.universityschedulebootsystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}