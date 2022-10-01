package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.TeacherDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class TeacherService implements CommonService<Teacher> {
    
    private final TeacherDao teacherDao;
    
    @Autowired
    public TeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
    @Override
    @Transactional
    public void add(Teacher teacher) {
        teacherDao.save(teacher);
    }
    
    @Override
    @Transactional
    public void addAll(List<Teacher> teacherList) {
        teacherDao.saveAll(teacherList);
    }
    
    @Override
    @Transactional
    public Teacher loadById(long id) {
        return teacherDao.loadById(id);
    }
    
    @Override
    @Transactional
    public List<Teacher> loadAll() {
        return teacherDao.loadAll();
    }
    
    @Override
    @Transactional
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        teacherDao.deleteById(id);
    }
}
