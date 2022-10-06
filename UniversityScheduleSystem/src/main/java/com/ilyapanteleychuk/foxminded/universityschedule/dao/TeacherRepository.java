package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}