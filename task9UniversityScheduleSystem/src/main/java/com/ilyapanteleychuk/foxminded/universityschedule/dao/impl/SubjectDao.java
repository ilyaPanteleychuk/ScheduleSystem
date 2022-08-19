package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class SubjectDao implements CommonDao<Subject> {
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("title");
    
    @Autowired
    public SubjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(Subject subject) {
        final String insertSql = "INSERT INTO university.subject VALUES(?)";
        jdbcTemplate.update(insertSql, subject.getSubjectTitle());
    }
    
    @Override
    public void addAll(List<Subject> subjectList) {
        final String insertSql = "INSERT INTO university.subject VALUES(?)";
        for(int i = 0; i <= subjectList.size(); i++){
            jdbcTemplate.update(insertSql, subjectList.get(i).getSubjectTitle());
        }
    }
    
    @Override
    public Subject get(Subject subject) {
        final String selectSql = "SELECT * FROM university.subject WHERE title = ?";
        return jdbcTemplate.query(selectSql, new Object[]{subject.getSubjectTitle()},
                new BeanPropertyRowMapper<>(Subject.class))
                .stream().findAny().orElse(null);
    }
    
    @Override
    public Subject getById(int id) {
        final String selectSql = "SELECT * FROM university.subject WHERE id = ?";
        return jdbcTemplate.query(selectSql, new Object[]{id},
                        new BeanPropertyRowMapper<>(Subject.class))
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Subject> getAll() {
        final String selectSql = "SELECT * FROM university.subject";
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Subject.class));
    }
    
    @Override
    public void update(int id, Subject subject) {
        final String updateSql = "UPDATE university.subject SET title = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, subject.getSubjectTitle(), id);
    }
    
    @Override
    public void delete(Subject subject) {
        final String deleteSql = "DELETE FROM university.subject WHERE title = ?";
        jdbcTemplate.update(deleteSql, subject.getSubjectTitle());
    }
    
    @Override
    public void deleteById(int id) {
        final String deleteSql = "DELETE FROM university.subject WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
    
    public static List<String> getColumns(){
        return columns;
    }
}