package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.FacultyDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class FacultyService implements CommonService<Faculty> {
    
    private final FacultyDaoImpl facultyDaoImpl;
    
    @Autowired
    public FacultyService(FacultyDaoImpl facultyDaoImpl) {
        this.facultyDaoImpl = facultyDaoImpl;
    }
    
    @Override
    @Transactional
    public void add(Faculty faculty) {
        facultyDaoImpl.save(faculty);
    }
    
    @Override
    @Transactional
    public void addAll(List<Faculty> faculties) {
        facultyDaoImpl.saveAll(faculties);
    }
    
    @Override
    @Transactional
    public Faculty loadById(long id) {
        return facultyDaoImpl.loadById(id);
    }
    
    @Override
    @Transactional
    public List<Faculty> loadAll() {
        return facultyDaoImpl.loadAll();
    }
    
    @Override
    @Transactional
    public void update(Faculty faculty) {
        facultyDaoImpl.update(faculty);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        facultyDaoImpl.deleteById(id);
    }
}
