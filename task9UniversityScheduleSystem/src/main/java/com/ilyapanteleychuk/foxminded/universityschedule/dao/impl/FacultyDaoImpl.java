package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class FacultyDaoImpl implements CommonDao<Faculty> {
    
    private static final List<String> COLUMNS =
            List.of("faculty.id as id_faculty", "faculty.title as title_faculty");
    private static final String INSERT_SQL =
            "INSERT INTO university.faculty VALUES(?) ";
    private static final String SELECT_SQL =
            "SELECT * FROM university.faculty ";
    private static final String UPDATE_SQL =
            "UPDATE university.faculty SET title = ? ";
    private static final String DELETE_SQL =
            "DELETE FROM university.faculty ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public FacultyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(Faculty faculty) {
        jdbcTemplate.update(INSERT_SQL, faculty.getTitle());
    }
    
    @Override
    public void addAll(List<Faculty> facultyList) {
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, facultyList.get(i).getTitle());
            }
    
            @Override
            public int getBatchSize() {
                return facultyList.size();
            }
        });
    }
    
    @Override
    public Faculty load(Faculty faculty) {
        String whereClause = "WHERE title = ?";
        String query = SELECT_SQL + whereClause;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Faculty.class),
                faculty.getTitle()).stream().findAny().orElse(null);
    }
    
    @Override
    public Faculty loadById(long id) {
        String whereClause = "WHERE id = ?";
        String query = SELECT_SQL + whereClause;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Faculty.class),
                id).stream().findAny().orElse(null);
    }
    
    @Override
    public List<Faculty> loadAll() {
        return jdbcTemplate.query(SELECT_SQL, new BeanPropertyRowMapper<>(Faculty.class));
    }
    
    @Override
    public void update(long id, Faculty faculty) {
        String whereClause = "WHERE id = ?";
        String query = UPDATE_SQL + whereClause;
        jdbcTemplate.update(query, faculty.getTitle(), id);
    }
    
    @Override
    public void delete(Faculty faculty) {
        String whereClause = "WHERE title = ?";
        String query = DELETE_SQL + whereClause;
        jdbcTemplate.update(query, faculty.getTitle());
    }
    
    @Override
    public void deleteById(long id) {
        String whereClause = "WHERE id = ?";
        String query = DELETE_SQL + whereClause;
        jdbcTemplate.update(query, id);
    }
    
    public static List<String> getColumns(){
        return COLUMNS;
    }
}
