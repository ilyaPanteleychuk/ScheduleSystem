package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.SubjectDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectService implements CommonService<Subject> {
    
    private final SubjectDao subjectDao;
    
    @Autowired
    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }
    
    
    @Override
    public void add(Subject subject) {
        subjectDao.add(subject);
    }
    
    @Override
    public void addAll(List<Subject> subjects) {
        subjectDao.addAll(subjects);
    }
    
    @Override
    public Subject load(Subject subject) {
        return subjectDao.load(subject);
    }
    
    @Override
    public Subject loadById(long id) {
        return subjectDao.loadById(id);
    }
    
    @Override
    public List<Subject> loadAll() {
        return subjectDao.loadAll();
    }
    
    @Override
    public void update(long id, Subject subject) {
        subjectDao.update(id, subject);
    }
    
    @Override
    public void delete(Subject subject) {
        subjectDao.delete(subject);
    }
    
    @Override
    public void deleteById(long id) {
        subjectDao.deleteById(id);
    }
}
