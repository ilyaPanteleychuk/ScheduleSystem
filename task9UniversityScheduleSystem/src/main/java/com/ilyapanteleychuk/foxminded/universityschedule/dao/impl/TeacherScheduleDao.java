package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper.TeacherScheduleMapper;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class TeacherScheduleDao implements CommonDao<TeacherSchedule> {
    
    private static final List<String> COLUMNS = List.of("teacher_id");
    private static final String INSERT_SQL =
            "INSERT INTO university.schedule(%s) VALUES(?) ";
    private static final String SELECT_SQL =
            "SELECT id, teacher_id, %s FROM university.schedule ";
    private static final String UPDATE_SQL =
            "UPDATE university.schedule SET teacher_id WHERE id = ?";
    private static final String DELETE_SQL =
            "DELETE FROM university.schedule ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public TeacherScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(TeacherSchedule teacherSchedule) {
        String query = String.format(INSERT_SQL, COLUMNS);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.update(query, teacherSchedule.getTeacher().getId());
    }
    
    @Override
    public void addAll(List<TeacherSchedule> teacherSchedules) {
        String query = String.format(INSERT_SQL, COLUMNS);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, teacherSchedules.get(i).getTeacher().getId());
            }
    
            @Override
            public int getBatchSize() {
                return teacherSchedules.size();
            }
        });
    }
    
    @Override
    public TeacherSchedule load(TeacherSchedule teacherSchedule) {
        String whereClause = "WHERE teacher_id = ?";
        String sql = String.format(SELECT_SQL, TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new TeacherScheduleMapper(),
                teacherSchedule.getTeacher().getId())
                .stream().findAny().orElse(null);
    }
    
    @Override
    public TeacherSchedule loadById(long id) {
        String whereClause = "WHERE id = ?";
        String sql = String.format(SELECT_SQL, TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new TeacherScheduleMapper(),
                        id).stream().findAny().orElse(null);
    }
    
    @Override
    public List<TeacherSchedule> loadAll() {
        String sql = String.format(SELECT_SQL, TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "");
        return jdbcTemplate.query(query, new TeacherScheduleMapper());
    }
    
    @Override
    public void update(long id, TeacherSchedule teacherSchedule) {
        jdbcTemplate.update(UPDATE_SQL, teacherSchedule.getTeacher().getId(), id);
    }
    
    @Override
    public void delete(TeacherSchedule teacherSchedule) {
        String whereClause = "WHERE teacher_id = ?";
        String query = DELETE_SQL + whereClause;
        jdbcTemplate.update(query, teacherSchedule.getTeacher().getId());
    }
    
    @Override
    public void deleteById(long id) {
        String whereClause = "WHERE id = ?";
        String query = DELETE_SQL + whereClause;
        jdbcTemplate.update(query, id);
    }
}
