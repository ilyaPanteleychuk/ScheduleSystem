package com.ilyapanteleychuk.universityschedulebootsystem.service.impl;

import com.ilyapanteleychuk.universityschedulebootsystem.dao.AudienceRepository;
import com.ilyapanteleychuk.universityschedulebootsystem.entity.Audience;
import com.ilyapanteleychuk.universityschedulebootsystem.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class AudienceService implements CommonService<Audience> {
    
    private final AudienceRepository audienceRepository;
    
    @Autowired
    public AudienceService(AudienceRepository audienceRepository) {
        this.audienceRepository = audienceRepository;
    }
    
    @Override
    @Transactional
    public void save(Audience audience) {
        audienceRepository.save(audience);
    }
    
    @Override
    @Transactional
    public void saveAll(List<Audience> audienceList) {
        audienceRepository.saveAll(audienceList);
    }
    
    @Override
    @Transactional
    public Audience findById(long id) {
        Optional<Audience> optionalAudience = audienceRepository.findById(id);
        return optionalAudience.orElseGet(() -> new Audience(0));
    }
    
    @Override
    @Transactional
    public List<Audience> findAll() {
        return audienceRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(Audience audience) {
        audienceRepository.save(audience);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        audienceRepository.deleteById(id);
    }
}
