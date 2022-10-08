package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.TeacherRepository;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class TeacherService implements CommonService<Teacher> {
    
    private final TeacherRepository teacherRepository;
    
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    
    @Override
    @Transactional
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    
    @Override
    @Transactional
    public void saveAll(List<Teacher> teacherList) {
        teacherRepository.saveAll(teacherList);
    }
    
    @Override
    @Transactional
    public Teacher findById(long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElseGet(() -> new Teacher("default", "default"));
    }
    
    @Override
    @Transactional
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        teacherRepository.deleteById(id);
    }
}
