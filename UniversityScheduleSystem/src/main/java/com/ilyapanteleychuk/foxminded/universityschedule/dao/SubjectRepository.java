package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SubjectRepository extends JpaRepository<Subject, Long> {

}