package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.TeacherDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TeacherService implements CommonService<Teacher> {
    
    private final TeacherDao teacherDao;
    
    @Autowired
    public TeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    
    @Override
    public void add(Teacher teacher) {
        teacherDao.add(teacher);
    }
    
    @Override
    public void addAll(List<Teacher> teacherList) {
        teacherDao.addAll(teacherList);
    }
    
    @Override
    public Teacher load(Teacher teacher) {
        return teacherDao.load(teacher);
    }
    
    @Override
    public Teacher loadById(long id) {
        return teacherDao.loadById(id);
    }
    
    @Override
    public List<Teacher> loadAll() {
        return teacherDao.loadAll();
    }
    
    @Override
    public void update(long id, Teacher teacher) {
        teacherDao.update(id, teacher);
    }
    
    @Override
    public void delete(Teacher teacher) {
        teacherDao.delete(teacher);
    }
    
    @Override
    public void deleteById(long id) {
        teacherDao.deleteById(id);
    }
}
