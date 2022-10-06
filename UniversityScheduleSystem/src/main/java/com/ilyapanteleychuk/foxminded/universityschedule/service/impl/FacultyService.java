package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.FacultyRepository;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class FacultyService implements CommonService<Faculty> {
    
    private final FacultyRepository facultyRepository;
    
    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    
    @Override
    @Transactional
    public void save(Faculty faculty) {
        facultyRepository.save(faculty);
    }
    
    @Override
    @Transactional
    public void saveAll(List<Faculty> faculties) {
        facultyRepository.saveAll(faculties);
    }
    
    @Override
    @Transactional
    public Faculty findById(long id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if(optionalFaculty.isPresent()){
            return optionalFaculty.get();
        }else{
            return new Faculty("default");
        }
    }
    
    @Override
    @Transactional
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(Faculty faculty) {
        facultyRepository.save(faculty);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        facultyRepository.deleteById(id);
    }
}
