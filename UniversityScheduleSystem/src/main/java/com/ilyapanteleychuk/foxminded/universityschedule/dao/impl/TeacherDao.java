package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class TeacherDao implements CommonDao<Teacher> {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public TeacherDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Teacher teacher) {
        sessionFactory.getCurrentSession().save(teacher);
    }
    
    @Override
    public void saveAll(List<Teacher> teacherList) {
        Session session = sessionFactory.getCurrentSession();
        for(int i = 0; i < teacherList.size(); i++){
            session.save(teacherList.get(i));
            if(i % 25 == 0){
                session.flush();
                session.clear();
            }
        }
    }
    
    @Override
    public Teacher loadById(long id) {
        return sessionFactory.getCurrentSession().load(Teacher.class, id);
    }
    
    @Override
    public List<Teacher> loadAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Teacher ").getResultList();
    }
    
    @Override
    public void update(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        session.update(teacher);
    }
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = session.load(Teacher.class, id);
        session.delete(teacher);
    }
}
