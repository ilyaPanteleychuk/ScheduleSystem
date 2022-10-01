package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class SubjectDao implements CommonDao<Subject> {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public SubjectDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Subject subject) {
        sessionFactory.getCurrentSession().save(subject);
    }
    
    @Override
    public void saveAll(List<Subject> subjectList) {
        Session session = sessionFactory.getCurrentSession();
        for(int i = 0; i < subjectList.size(); i++){
            session.save(subjectList.get(i));
            if(i % 25 == 0){
                session.flush();
                session.clear();
            }
        }
    }
    
    @Override
    public Subject loadById(long id) {
        return sessionFactory.getCurrentSession().load(Subject.class, id);
    }
    
    @Override
    public List<Subject> loadAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Subject ").getResultList();
    }
    
    @Override
    public void update(Subject subject) {
        Session session = sessionFactory.getCurrentSession();
        session.update(subject);
    }
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Subject subject = session.load(Subject.class, id);
        session.delete(subject);
    }
}
