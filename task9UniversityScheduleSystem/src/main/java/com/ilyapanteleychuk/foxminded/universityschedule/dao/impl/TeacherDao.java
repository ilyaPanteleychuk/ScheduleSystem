package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class TeacherDao implements CommonDao<Teacher> {
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("first_name, last_name");
    
    @Autowired
    public TeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    public void add(Teacher teacher) {
        final String insertSql = "INSERT INTO university.teacher VALUES(?, ?)";
        jdbcTemplate.update(insertSql, teacher.getFirstName(), teacher.getLastName());
    }
    
    @Override
    public void addAll(List<Teacher> teacherList) {
        final String insertSql = "INSERT INTO university.teacher VALUES(?, ?)";
        for(int i = 0; i <= teacherList.size(); i++){
            jdbcTemplate.update(insertSql, teacherList.get(i).getFirstName(),
                    teacherList.get(i).getLastName());
        }
    }
    
    @Override
    public Teacher get(Teacher teacher) {
        final String selectSql = "SELECT * FROM university.teacher " +
                "WHERE first_name = ? AND last_name = ?";
        return jdbcTemplate.query(selectSql,
                        new Object[]{teacher.getFirstName(), teacher.getLastName()},
                new BeanPropertyRowMapper<>(Teacher.class))
                .stream().findAny().orElse(null);
    }
    
    @Override
    public Teacher getById(int id) {
        final String selectSql = "SELECT * FROM university.teacher WHERE id = ?";
        return jdbcTemplate.query(selectSql, new Object[]{id},
                        new BeanPropertyRowMapper<>(Teacher.class))
                        .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Teacher> getAll() {
        final String selectSql = "SELECT * FROM university.teacher";
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Teacher.class));
    }
    
    @Override
    public void update(int id, Teacher teacher) {
        final String updateSql = "UPDATE university.teacher " +
                "SET first_name = ?, last_name = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, teacher.getFirstName(), teacher.getLastName(), id);
    }
    
    @Override
    public void delete(Teacher teacher) {
        final String deleteSql = "DELETE FROM university.teacher " +
                "WHERE first_name = ? AND last_name = ?";
        jdbcTemplate.update(deleteSql, teacher.getFirstName(), teacher.getLastName());
    }
    
    @Override
    public void deleteById(int id) {
        final String deleteSql = "DELETE FROM university.teacher WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
    
    public static List<String> getColumns(){
        return columns;
    }
}
