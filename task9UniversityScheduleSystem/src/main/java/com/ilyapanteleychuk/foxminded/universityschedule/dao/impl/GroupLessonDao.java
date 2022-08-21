package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper.GroupLessonMapper;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class GroupLessonDao implements CommonDao<GroupLesson> {
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("audience_id", "subject_id",
            "date", "type", "teacher_id", "lesson_order", "schedule_id");
    private static final String SELECT_SQL = "SELECT %s, %s, %s, %s FROM university.lesson " +
            "INNER JOIN university.audience ON lesson.audience_id = audience.id " +
            "INNER JOIN university.subject ON lesson.subject_id = subject.id " +
            "INNER JOIN university.teacher ON lesson.teacher_id = teacher.id ";
    
    @Autowired
    public GroupLessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(GroupLesson groupLesson) {
        final String INSERT_SQL = "INSERT INTO university.lesson(%s) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        String query = String.format(INSERT_SQL, GroupLessonDao.columns);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.update(query,
                groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(),
                groupLesson.getDate(),
                groupLesson.getType(),
                groupLesson.getTeacher().getId(),
                groupLesson.getOrder(),
                groupLesson.getSchedule().getId());
    }
    
    @Override
    public void addAll(List<GroupLesson> lessons) {
        final String INSERT_SQL = "INSERT INTO university.lesson(%s) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        String query = String.format(INSERT_SQL, GroupLessonDao.columns);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, lessons.get(i).getAudience().getId());
                ps.setLong(2, lessons.get(i).getSubject().getId());
                ps.setObject(3, lessons.get(i).getDate());
                ps.setString(4, lessons.get(i).getType());
                ps.setLong(5, lessons.get(i).getTeacher().getId());
                ps.setInt(6, lessons.get(i).getOrder());
                ps.setLong(7, lessons.get(i).getSchedule().getId());
            }
            @Override
            public int getBatchSize() {
                return lessons.size();
            }
        });
    }
    
    @Override
    public GroupLesson get(GroupLesson groupLesson) {
        String sql = String.format(SELECT_SQL, GroupLessonDao.columns,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE audience_id = ? AND subject_id = ? " +
                "AND lesson_date = ? AND lesson_type = ? AND teacher_id = ? " +
                "AND lesson_order = ? AND schedule_id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new GroupLessonMapper(),
                groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(),
                groupLesson.getDate(),
                groupLesson.getType(),
                groupLesson.getTeacher().getId(),
                groupLesson.getOrder(),
                groupLesson.getSchedule().getId()
        ).stream().findFirst().orElse(null);
    }
    
    @Override
    public GroupLesson getById(int id) {
        String sql = String.format(SELECT_SQL, GroupLessonDao.columns,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE lesson.id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query,
                 new GroupLessonMapper(), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<GroupLesson> getAll() {
        String sql = String.format(SELECT_SQL, GroupLessonDao.columns,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "");
        return jdbcTemplate.query(query, new GroupLessonMapper());
    }
    
    @Override
    public void update(int id, GroupLesson groupLesson) {
        final String updateSql = "UPDATE university.lesson SET audience_id = ?, " +
                "subject_id = ?, lesson_date = ?, lesson_type = ?, teacher_id = ?, " +
                "lesson_order = ?, schedule_id = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(), groupLesson.getDate(),
                groupLesson.getType(), groupLesson.getTeacher().getId(),
                groupLesson.getOrder(), groupLesson.getSchedule().getId(), id);
    }
    
    @Override
    public void delete(GroupLesson groupLesson) {
        final String deleteSql = "DELETE FROM university.lesson " +
                "WHERE audience_id = ? AND subject_id = ? AND lesson_date = ? " +
                "AND lesson_type = ? AND teacher_id = ? " +
                "AND lesson_order = ? AND schedule_id = ? ";
        jdbcTemplate.update(deleteSql,  groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(), groupLesson.getDate(),
                groupLesson.getType(), groupLesson.getTeacher().getId(),
                groupLesson.getOrder(), groupLesson.getSchedule().getId());
    }
    
    @Override
    public void deleteById(int id) {
        final String deleteSql = "DELETE FROM university.lesson WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
    
    public static List<String> getColumns(){
        return columns;
    }
}
