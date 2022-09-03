package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class TeacherDao implements CommonDao<Teacher> {
    
    private static final List<String> COLUMNS = List.of("first_name, last_name");
    private static final String INSERT_SQL =
            "INSERT INTO university.teacher VALUES(?, ?)";
    private static final String SELECT_SQL =
            "SELECT * FROM university.teacher ";
    private static final String UPDATE_SQL =
            "UPDATE university.teacher SET first_name = ?, last_name = ? ";
    private static final String DELETE_SQL =
            "DELETE FROM university.teacher ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public TeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    public void add(Teacher teacher) {
        jdbcTemplate.update(INSERT_SQL, teacher.getFirstName(), teacher.getLastName());
    }
    
    @Override
    public void addAll(List<Teacher> teacherList) {
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, teacherList.get(i).getFirstName());
                ps.setString(2, teacherList.get(i).getLastName());
            }
    
            @Override
            public int getBatchSize() {
                return teacherList.size();
            }
        });
    }
    
    @Override
    public Teacher load(Teacher teacher) {
        String whereClause = "WHERE first_name AND last_name = ?";
        String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql,
                        new BeanPropertyRowMapper<>(Teacher.class),
                        teacher.getFirstName(), teacher.getLastName())
                .stream().findAny().orElse(null);
    }
    
    @Override
    public Teacher loadById(long id) {
        String whereClause = "WHERE id = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql,
                        new BeanPropertyRowMapper<>(Teacher.class), id)
                        .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Teacher> loadAll() {
        return jdbcTemplate.query(SELECT_SQL, new BeanPropertyRowMapper<>(Teacher.class));
    }
    
    @Override
    public void update(long id, Teacher teacher) {
        String whereClause = "WHERE id = ?";
        final String updateSql = UPDATE_SQL + whereClause;
        jdbcTemplate.update(updateSql, teacher.getFirstName(), teacher.getLastName(), id);
    }
    
    @Override
    public void delete(Teacher teacher) {
        String whereClause = "WHERE first_name = ? AND last_name = ?";
        final String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql, teacher.getFirstName(), teacher.getLastName());
    }
    
    @Override
    public void deleteById(long id) {
        String whereClause = "WHERE id = ?";
        final String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql, id);
    }
    
    public static List<String> getColumns(){
        return COLUMNS;
    }
}
