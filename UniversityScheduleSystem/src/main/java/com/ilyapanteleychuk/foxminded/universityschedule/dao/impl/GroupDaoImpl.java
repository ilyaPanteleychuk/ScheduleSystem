package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.GroupDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class GroupDaoImpl implements CommonDao<Group>, GroupDao {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Group> loadGroupsByFacultyId(long facultyId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Group where faculty.id=:facultyId")
                .setParameter("facultyId", facultyId)
                .getResultList();
    }
    
    @Override
    public void save(Group group) {
        sessionFactory.getCurrentSession().save(group);
    }
    
    @Override
    public void saveAll(List<Group> groupList) {
        sessionFactory.getCurrentSession().save(groupList);
    }
    
    @Override
    public Group loadById(long id) {
        return sessionFactory.getCurrentSession().load(Group.class, id);
    }
    
    @Override
    public List<Group> loadAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Group ").getResultList();
    }
    
    @Override
    public void update(Group group) {
        sessionFactory.getCurrentSession().update(group);
    }
    
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Group group = session.load(Group.class, id);
        session.delete(group);
    }
}
