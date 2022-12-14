package com.ilyapanteleychuk.universityschedulebootsystem.service.impl;

import com.ilyapanteleychuk.universityschedulebootsystem.dao.SubjectRepository;
import com.ilyapanteleychuk.universityschedulebootsystem.entity.Subject;
import com.ilyapanteleychuk.universityschedulebootsystem.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class SubjectService implements CommonService<Subject> {
    
    private final SubjectRepository subjectRepository;
    
    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    
    @Override
    @Transactional
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }
    
    @Override
    @Transactional
    public void saveAll(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }
    
    @Override
    @Transactional
    public Subject findById(long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElseGet(() -> new Subject("default"));
    }
    
    @Override
    @Transactional
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(Subject subject) {
        subjectRepository.save(subject);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        subjectRepository.deleteById(id);
    }
}
