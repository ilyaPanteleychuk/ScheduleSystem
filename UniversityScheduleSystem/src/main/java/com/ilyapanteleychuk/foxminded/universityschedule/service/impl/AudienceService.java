package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.AudienceDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class AudienceService implements CommonService<Audience> {
    
    private final AudienceDao audienceDao;
    
    @Autowired
    public AudienceService(AudienceDao audienceDao) {
        this.audienceDao = audienceDao;
    }
    
    @Override
    @Transactional
    public void add(Audience audience) {
        audienceDao.save(audience);
    }
    
    @Override
    @Transactional
    public void addAll(List<Audience> audienceList) {
        audienceDao.saveAll(audienceList);
    }
    
    @Override
    @Transactional
    public Audience loadById(long id) {
        return audienceDao.loadById(id);
    }
    
    @Override
    @Transactional
    public List<Audience> loadAll() {
        return audienceDao.loadAll();
    }
    
    @Override
    @Transactional
    public void update(Audience audience) {
        audienceDao.update( audience);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        audienceDao.deleteById(id);
    }
}
