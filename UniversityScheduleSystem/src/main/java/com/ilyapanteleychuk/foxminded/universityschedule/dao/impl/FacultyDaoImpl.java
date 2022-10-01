package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FacultyDaoImpl implements CommonDao<Faculty> {
    
    private static final List<String> COLUMNS =
            List.of("faculty.id as id_faculty", "faculty.title as title_faculty");
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public FacultyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Faculty faculty) {
        sessionFactory.getCurrentSession().save(faculty);
    }
    
    @Override
    public void saveAll(List<Faculty> facultyList) {
        Session session = sessionFactory.getCurrentSession();
        for(int i = 0; i < facultyList.size(); i++){
            session.save(facultyList.get(i));
            if(i % 25 == 0){
                session.flush();
                session.clear();
            }
        }
    }
    
    @Override
    public Faculty loadById(long id) {
        return sessionFactory.getCurrentSession().load(Faculty.class, id);
    }
    
    @Override
    public List<Faculty> loadAll() {
        return sessionFactory.getCurrentSession().
                createQuery("from Faculty ").getResultList();
    }
    
    @Override
    public void update(Faculty faculty) {
        Session session = sessionFactory.getCurrentSession();
        session.update(faculty);
    }
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Faculty faculty = session.load(Faculty.class, id);
        session.delete(faculty);
    }
    
    public static List<String> getColumns(){
        return COLUMNS;
    }
}
