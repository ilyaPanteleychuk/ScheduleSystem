package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.LessonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Repository
public class TeacherLessonDao implements
        CommonDao<TeacherLesson>, LessonDao<TeacherLesson> {
    
    private static final String SELECT_HQL =
            "SELECT gl FROM TeacherLesson gl " +
                    "LEFT JOIN FETCH gl.audience " +
                    "LEFT JOIN FETCH gl.subject " +
                    "LEFT JOIN FETCH gl.group ";
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public TeacherLessonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<TeacherLesson> loadLessonsPerWeek(long id) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate endOfTheWeek = today.plusDays(7);
        String query = "from TeacherLesson gl " +
                "where gl.date between :today and :endOfTheWeek";
        return sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("today", today)
                .setParameter("endOfTheWeek", endOfTheWeek)
                .getResultList();
    }
    
    @Override
    public void save(TeacherLesson teacherLesson) {
        sessionFactory.getCurrentSession().save(teacherLesson);
    }
    
    @Override
    public void saveAll(List<TeacherLesson> lessons) {
        sessionFactory.getCurrentSession().save(lessons);
    }
    
    @Override
    public TeacherLesson loadById(long id) {
        String query = SELECT_HQL +  " where gl.id=: id";
        return (TeacherLesson) sessionFactory.getCurrentSession()
                .createQuery(query).setParameter("id", id)
                .getResultList().stream().findAny().orElse(null);
    }
    
    @Override
    public List<TeacherLesson> loadAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from TeacherLesson ")
                .getResultList();
    }
    
    @Override
    public void update(TeacherLesson teacherLesson) {
        sessionFactory.getCurrentSession().update(teacherLesson);
    }
    
    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        TeacherLesson teacherLesson = session.get(TeacherLesson.class, id);
        session.delete(teacherLesson);
    }
}
