package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AudienceRepository extends JpaRepository<Audience, Long> {

}