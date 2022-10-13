package com.ilyapanteleychuk.universityschedulebootsystem.dao;

import com.ilyapanteleychuk.universityschedulebootsystem.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AudienceRepository extends JpaRepository<Audience, Long> {

}