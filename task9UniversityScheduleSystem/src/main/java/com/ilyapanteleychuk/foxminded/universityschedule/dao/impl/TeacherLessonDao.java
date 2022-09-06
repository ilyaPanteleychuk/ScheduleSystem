package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.LessonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper.TeacherLessonMapper;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class TeacherLessonDao implements CommonDao<TeacherLesson>, LessonDao<TeacherLesson> {
    
    private static final List<String> COLUMNS = List.of("audience_id", "subject_id",
            "date", "type", "group_id", "lesson_order");
    private static final String INSERT_SQL =
            "INSERT INTO university.lesson(%s) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SQL = "SELECT %s, %s, %s, %s FROM university.lesson " +
            "INNER JOIN university.audience ON lesson.audience_id = audience.id " +
            "INNER JOIN university.subject ON lesson.subject_id = subject.id " +
            "INNER JOIN university.groups ON lesson.group_id = groups.id ";
    private static final String UPDATE_SQL =
            "UPDATE university.lesson SET audience_id = ?, " +
            "subject_id = ?, date = ?, type = ?, group_id = ?, " +
            "lesson_order = ? ";
    private static final String DELETE_SQL = "DELETE FROM university.lesson ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public TeacherLessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<TeacherLesson> loadByUserIdPerTimePeriod(long id, int timePeriod) {
        String sql = String.format(SELECT_SQL, COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                GroupDao.getColumns());
        String whereClause = "WHERE date >= NOW() " +
                "AND date < (NOW + (INTERVAL '1 day' * ?)) " +
                "AND teacher_id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new TeacherLessonMapper(), timePeriod, id);
    }
    
    @Override
    public void add(TeacherLesson teacherLesson) {
        String query = String.format(INSERT_SQL, COLUMNS);
        query = query.replace("[", "")
                .replace("]", "");
        jdbcTemplate.update(query,
                teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(),
                teacherLesson.getDate(),
                teacherLesson.getType(),
                teacherLesson.getGroup().getId(),
                teacherLesson.getOrder());
    }
    
    @Override
    public void addAll(List<TeacherLesson> lessons) {
        String query = String.format(INSERT_SQL, COLUMNS);
        query = query.replace("[", "")
                .replace("]", "");
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, lessons.get(i).getAudience().getId());
                ps.setLong(2, lessons.get(i).getSubject().getId());
                ps.setObject(3, lessons.get(i).getDate());
                ps.setString(4, lessons.get(i).getType());
                ps.setLong(5, lessons.get(i).getGroup().getId());
                ps.setInt(6, lessons.get(i).getOrder());
            }
            @Override
            public int getBatchSize() {
                return lessons.size();
            }
        });
    }
    
    @Override
    public TeacherLesson load(TeacherLesson teacherLesson) {
        String sql = String.format(SELECT_SQL, TeacherLessonDao.COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE audience_id = ? AND subject_id = ? " +
                "AND lesson_date = ? AND lesson_type = ? AND group_id = ? " +
                "AND lesson_order = ? ";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new TeacherLessonMapper(),
                        teacherLesson.getAudience().getId(),
                        teacherLesson.getSubject().getId(),
                        teacherLesson.getDate(),
                        teacherLesson.getType(),
                        teacherLesson.getGroup().getId(),
                        teacherLesson.getOrder()
                ).stream().findAny().orElse(null);
    }
    
    @Override
    public TeacherLesson loadById(long id) {
        String sql = String.format(SELECT_SQL, TeacherLessonDao.COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE lesson.id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new TeacherLessonMapper(), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<TeacherLesson> loadAll() {
        String sql = String.format(SELECT_SQL, TeacherLessonDao.COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "");
        return jdbcTemplate.query(query, new TeacherLessonMapper());
    }
    
    @Override
    public void update(long id, TeacherLesson teacherLesson) {
        String whereClause = "WHERE id = ?";
        final String updateSql = UPDATE_SQL + whereClause;
        jdbcTemplate.update(updateSql, teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(), teacherLesson.getDate(),
                teacherLesson.getType(), teacherLesson.getGroup().getId(),
                teacherLesson.getOrder(), id);
    }
    
    @Override
    public void delete(TeacherLesson teacherLesson) {
        String whereClause =
                "WHERE audience_id = ? AND subject_id = ? AND lesson_date = ? " +
                "AND lesson_type = ? AND group_id = ? " +
                "AND lesson_order = ? ";
        final String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql,  teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(), teacherLesson.getDate(),
                teacherLesson.getType(), teacherLesson.getGroup().getId(),
                teacherLesson.getOrder());
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
