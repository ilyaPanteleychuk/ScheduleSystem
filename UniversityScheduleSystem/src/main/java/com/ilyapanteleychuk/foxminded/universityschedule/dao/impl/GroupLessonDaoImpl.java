package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.LessonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Repository
public class GroupLessonDaoImpl implements
        CommonDao<GroupLesson>, LessonDao<GroupLesson> {
    
    private static final String SELECT_HQL =
            "SELECT gl FROM GroupLesson gl " +
            "LEFT JOIN FETCH gl.audience " +
            "LEFT JOIN FETCH gl.subject " +
            "LEFT JOIN FETCH gl.teacher ";
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public GroupLessonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<GroupLesson> loadLessonsPerWeek(long groupId) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate endOfTheWeek = today.plusDays(7);
        String query = "from GroupLesson gl " +
                "where gl.date between :today and :endOfTheWeek";
        return sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("today", today)
                .setParameter("endOfTheWeek", endOfTheWeek)
                .getResultList();
    }
    
    @Override
    public void save(GroupLesson groupLesson) {
        sessionFactory.getCurrentSession().save(groupLesson);
    }
    
    @Override
    public void saveAll(List<GroupLesson> lessons) {
        sessionFactory.getCurrentSession().save(lessons);
    }
    
    @Override
    public GroupLesson loadById(long id) {
        String query = SELECT_HQL +  " where gl.id=: id";
        return (GroupLesson) sessionFactory.getCurrentSession()
                .createQuery(query).setParameter("id", id)
                .getResultList().stream().findAny().orElse(null);
    }
    
    @Override
    public List<GroupLesson> loadAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from GroupLesson ").getResultList();
    }
    
    @Override
    public void update(GroupLesson groupLesson) {
        sessionFactory.getCurrentSession().update(groupLesson);
    }
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        GroupLesson groupLesson = session.load(GroupLesson.class, id);
        session.delete(groupLesson);
    }
}
