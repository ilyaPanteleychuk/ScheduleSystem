package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class AudienceDao implements CommonDao<Audience> {
    
    private static final List<String> COLUMNS =
            List.of("audience.id", "audience_number");
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public AudienceDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(Audience audience) {
        sessionFactory.getCurrentSession().save(audience);
    }
    
    @Override
    public void saveAll(List<Audience> audienceList) {
        Session session = sessionFactory.getCurrentSession();
        for(int i = 0; i < audienceList.size(); i++){
            session.save(audienceList.get(i));
            if(i % 25 == 0){
                session.flush();
                session.clear();
            }
        }
    }
    
    @Override
    public Audience loadById(long id) {
        return sessionFactory.getCurrentSession().load(Audience.class, id);
    }
    
    @Override
    public List<Audience> loadAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Audience ").getResultList();
    }
    
    @Override
    public void update(Audience audience) {
        Session session = sessionFactory.getCurrentSession();
        session.update(audience);
    }
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Audience audience = session.load(Audience.class, id);
        session.delete(audience);
    }
    
    public static List<String> getColumns(){
        return COLUMNS;
    }
}
