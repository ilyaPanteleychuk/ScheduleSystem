package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.FacultyDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService implements CommonService<Faculty> {
    
    private final FacultyDaoImpl facultyDaoImpl;
    
    @Autowired
    public FacultyService(FacultyDaoImpl facultyDaoImpl) {
        this.facultyDaoImpl = facultyDaoImpl;
    }
    
    @Override
    public void add(Faculty faculty) {
        facultyDaoImpl.add(faculty);
    }
    
    @Override
    public void addAll(List<Faculty> faculties) {
        facultyDaoImpl.addAll(faculties);
    }
    
    @Override
    public Faculty load(Faculty faculty) {
        return facultyDaoImpl.load(faculty);
    }
    
    @Override
    public Faculty loadById(long id) {
        return facultyDaoImpl.loadById(id);
    }
    
    @Override
    public List<Faculty> loadAll() {
        return facultyDaoImpl.loadAll();
    }
    
    @Override
    public void update(long id, Faculty faculty) {
        facultyDaoImpl.update(id, faculty);
    }
    
    @Override
    public void delete(Faculty faculty) {
        facultyDaoImpl.delete(faculty);
    }
    
    @Override
    public void deleteById(long id) {
        facultyDaoImpl.deleteById(id);
    }
}
