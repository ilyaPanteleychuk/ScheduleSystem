package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class SubjectDao implements CommonDao<Subject> {
    
    private static final List<String> COLUMNS = List.of("title");
    private static final String INSERT_SQL =
            "INSERT INTO university.subject VALUES(?) ";
    private static final String SELECT_SQL =
            "SELECT * FROM university.subject ";
    private static final String UPDATE_SQL =
            "UPDATE university.subject SET title = ? ";
    private static final String DELETE_SQL =
            "DELETE FROM university.subject ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public SubjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(Subject subject) {
        jdbcTemplate.update(INSERT_SQL, subject.getTitle());
    }
    
    @Override
    public void addAll(List<Subject> subjectList) {
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, subjectList.get(i).getTitle());
            }
    
            @Override
            public int getBatchSize() {
                return subjectList.size();
            }
        });
    }
    
    @Override
    public Subject load(Subject subject) {
        String whereClause = "WHERE title = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Subject.class),
                        subject.getTitle()).stream().findAny().orElse(null);
    }
    
    @Override
    public Subject loadById(long id) {
        String whereClause = "WHERE id = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql,
                        new BeanPropertyRowMapper<>(Subject.class), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Subject> loadAll() {
        return jdbcTemplate.query(SELECT_SQL, new BeanPropertyRowMapper<>(Subject.class));
    }
    
    @Override
    public void update(long id, Subject subject) {
        String whereClause = "WHERE id = ?";
        final String updateSql = UPDATE_SQL + whereClause;
        jdbcTemplate.update(updateSql, subject.getTitle(), id);
    }
    
    @Override
    public void delete(Subject subject) {
        String whereClause = "WHERE title = ?";
        final String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql, subject.getTitle());
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
