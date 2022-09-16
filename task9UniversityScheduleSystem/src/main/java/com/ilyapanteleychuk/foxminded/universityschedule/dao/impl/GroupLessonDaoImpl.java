package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.GroupLessonDao;
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
public class GroupLessonDaoImpl implements CommonDao<GroupLesson>, GroupLessonDao {
    
    private static final List<String> COLUMNS =
            List.of("lesson.id", "audience_id", "subject_id", "date", "type",
                    "teacher_id", "lesson_order");
    private static final String SELECT_SQL =
            "SELECT %s, %s, %s, %s FROM university.lesson " +
            "INNER JOIN university.audience ON lesson.audience_id = audience.id " +
            "INNER JOIN university.subject ON lesson.subject_id = subject.id " +
            "INNER JOIN university.teacher ON lesson.teacher_id = teacher.id ";
    private static final String INSERT_SQL =
            "INSERT INTO university.lesson(%s) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE university.lesson SET audience_id = ?, subject_id = ?, date = ?, " +
                    "type = ?, teacher_id = ?, lesson_order = ? ";
    private static final String DELETE_SQL =
            "DELETE FROM university.lesson ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GroupLessonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<GroupLesson> loadLessonsPerWeek(long groupId) {
        String sql = String.format(SELECT_SQL, GroupLessonDaoImpl.COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE date >=(NOW() - (interval '1 day')) AND " +
                "date <= (NOW() + (interval '1 day' * 7)) " +
                "AND group_id = ?";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new GroupLessonMapper(), groupId);
    }
    
    @Override
    public void add(GroupLesson groupLesson) {
        String query = String.format(INSERT_SQL, GroupLessonDaoImpl.COLUMNS);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.update(query,
                groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(),
                groupLesson.getDate(),
                groupLesson.getType(),
                groupLesson.getTeacher().getId(),
                groupLesson.getOrder());
    }
    
    @Override
    public void addAll(List<GroupLesson> lessons) {
        String query = String.format(INSERT_SQL, GroupLessonDaoImpl.COLUMNS);
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
            }
            @Override
            public int getBatchSize() {
                return lessons.size();
            }
        });
    }
    
    @Override
    public GroupLesson load(GroupLesson groupLesson) {
        String sql = String.format(SELECT_SQL, GroupLessonDaoImpl.COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        String whereClause = "WHERE audience_id = ? AND subject_id = ? " +
                "AND lesson_date = ? AND lesson_type = ? AND teacher_id = ? " +
                "AND lesson_order = ? ";
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new GroupLessonMapper(),
                groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(),
                groupLesson.getDate(),
                groupLesson.getType(),
                groupLesson.getTeacher().getId(),
                groupLesson.getOrder()
        ).stream().findFirst().orElse(null);
    }
    
    @Override
    public GroupLesson loadById(long id) {
        String sql = String.format(SELECT_SQL, GroupLessonDaoImpl.COLUMNS,
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
    public List<GroupLesson> loadAll() {
        String sql = String.format(SELECT_SQL, GroupLessonDaoImpl.COLUMNS,
                AudienceDao.getColumns(), SubjectDao.getColumns(),
                TeacherDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "");
        return jdbcTemplate.query(query, new GroupLessonMapper());
    }
    
    @Override
    public void update(long id, GroupLesson groupLesson) {
        String whereClause = "WHERE id = ?";
        final String updateSql = UPDATE_SQL + whereClause;
        jdbcTemplate.update(updateSql, groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(), groupLesson.getDate(),
                groupLesson.getType(), groupLesson.getTeacher().getId(),
                groupLesson.getOrder(), id);
    }
    
    @Override
    public void delete(GroupLesson groupLesson) {
        String whereClause =
                "WHERE audience_id = ? AND subject_id = ? AND lesson_date = ? " +
                "AND lesson_type = ? AND teacher_id = ? " +
                "AND lesson_order = ? ";
        String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql,  groupLesson.getAudience().getId(),
                groupLesson.getSubject().getId(), groupLesson.getDate(),
                groupLesson.getType(), groupLesson.getTeacher().getId(),
                groupLesson.getOrder());
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
