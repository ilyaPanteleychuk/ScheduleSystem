package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper.TeacherLessonMapper;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class TeacherLessonDao implements CommonDao<TeacherLesson> {
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("audience_id", "subject_id",
            "lesson_date", "lesson_type", "group_id", "lesson_order", "schedule_id");
    private static final String SELECT_SQL = "SELECT %s, %s, %s, %s FROM university.lesson " +
            "INNER JOIN university.audience ON lesson.audience_id = audience.id " +
            "INNER JOIN university.subject ON lesson.subject_id = subject.id " +
            "INNER JOIN university.groups ON lesson.group_id = groups.id ";
    
    @Autowired
    public TeacherLessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(TeacherLesson teacherLesson) {
        final String insertSql = "INSERT INTO university.lesson(%s)";
        String query = String.format(insertSql, columns);
        query = query.replace("[", "")
                .replace("]", "");
        jdbcTemplate.update(query,
                teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(),
                teacherLesson.getDate(),
                teacherLesson.getType(),
                teacherLesson.getGroup().getId(),
                teacherLesson.getOrder(),
                teacherLesson.getSchedule().getId());
    }
    
    @Override
    public void addAll(List<TeacherLesson> teacherLessons) {
        final String insertSql = "INSERT INTO university.lesson(%s)";
        String query = String.format(insertSql, columns);
        query = query.replace("[", "")
                .replace("]", "");
        for(TeacherLesson teacherLesson : teacherLessons){
            jdbcTemplate.update(query,
                    teacherLesson.getAudience().getId(),
                    teacherLesson.getSubject().getId(),
                    teacherLesson.getDate(), teacherLesson.getType(),
                    teacherLesson.getGroup().getId(),
                    teacherLesson.getOrder(),
                    teacherLesson.getSchedule().getId());
        }
    }
    
    @Override
    public TeacherLesson get(TeacherLesson teacherLesson) {
        String sql = String.format(SELECT_SQL, TeacherLessonDao.columns,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE audience_id = ? AND subject_id = ? " +
                "AND lesson_date = ? AND lesson_type = ? AND group_id = ? " +
                "AND lesson_order = ? AND schedule_id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new Object[]{
                teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(),
                teacherLesson.getDate(),
                teacherLesson.getType(),
                teacherLesson.getGroup().getId(),
                teacherLesson.getOrder(),
                teacherLesson.getSchedule().getId()}, new TeacherLessonMapper())
                .stream().findAny().orElse(null);
    }
    
    @Override
    public TeacherLesson getById(int id) {
        String sql = String.format(SELECT_SQL, TeacherLessonDao.columns,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE lesson.id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query,
                        new Object[]{id}, new TeacherLessonMapper())
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<TeacherLesson> getAll() {
        String sql = String.format(SELECT_SQL, TeacherLessonDao.columns,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "");
        return jdbcTemplate.query(query, new TeacherLessonMapper());
    }
    
    @Override
    public void update(int id, TeacherLesson teacherLesson) {
        final String updateSql = "UPDATE university.lesson SET audience_id = ?, " +
                "subject_id = ?, lesson_date = ?, lesson_type = ?, group_id = ?, " +
                "lesson_order = ?, schedule_id = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(), teacherLesson.getDate(),
                teacherLesson.getType(), teacherLesson.getGroup().getId(),
                teacherLesson.getOrder(), teacherLesson.getSchedule().getId(), id);
    }
    
    @Override
    public void delete(TeacherLesson teacherLesson) {
        final String deleteSql = "DELETE FROM university.lesson " +
                "WHERE audience_id = ? AND subject_id = ? AND lesson_date = ? " +
                "AND lesson_type = ? AND group_id = ? " +
                "AND lesson_order = ? AND schedule_id = ? ";
        jdbcTemplate.update(deleteSql,  teacherLesson.getAudience().getId(),
                teacherLesson.getSubject().getId(), teacherLesson.getDate(),
                teacherLesson.getType(), teacherLesson.getGroup().getId(),
                teacherLesson.getOrder(), teacherLesson.getSchedule().getId());
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
