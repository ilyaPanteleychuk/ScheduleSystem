package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.AudienceDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AudienceService implements CommonService<Audience> {
    
    private final AudienceDao audienceDao;
    
    @Autowired
    public AudienceService(AudienceDao audienceDao) {
        this.audienceDao = audienceDao;
    }
    
    
    @Override
    public void add(Audience audience) {
        audienceDao.add(audience);
    }
    
    @Override
    public void addAll(List<Audience> audienceList) {
        audienceDao.addAll(audienceList);
    }
    
    @Override
    public Audience load(Audience audience) {
        return audienceDao.load(audience);
    }
    
    @Override
    public Audience loadById(long id) {
        return audienceDao.loadById(id);
    }
    
    @Override
    public List<Audience> loadAll() {
        return audienceDao.loadAll();
    }
    
    @Override
    public void update(long id, Audience audience) {
        audienceDao.update(id, audience);
    }
    
    @Override
    public void delete(Audience audience) {
        audienceDao.delete(audience);
    }
    
    @Override
    public void deleteById(long id) {
        audienceDao.deleteById(id);
    }
}
