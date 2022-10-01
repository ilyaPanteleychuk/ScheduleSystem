package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.SubjectDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class SubjectService implements CommonService<Subject> {
    
    private final SubjectDao subjectDao;
    
    @Autowired
    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }
    
    @Override
    @Transactional
    public void add(Subject subject) {
        subjectDao.save(subject);
    }
    
    @Override
    @Transactional
    public void addAll(List<Subject> subjects) {
        subjectDao.saveAll(subjects);
    }
    
    @Override
    @Transactional
    public Subject loadById(long id) {
        return subjectDao.loadById(id);
    }
    
    @Override
    @Transactional
    public List<Subject> loadAll() {
        return subjectDao.loadAll();
    }
    
    @Override
    @Transactional
    public void update(Subject subject) {
        subjectDao.update(subject);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        subjectDao.deleteById(id);
    }
}
