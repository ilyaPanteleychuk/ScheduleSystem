package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.dao.mapper.GroupScheduleMapper;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class GroupScheduleDao implements CommonDao<GroupSchedule> {
    
    private static final List<String> COLUMNS = List.of("group_id");
    private static final String INSERT_SQL =
            "INSERT INTO university.schedule(%s) VALUES(?) ";
    private static final String SELECT_SQL =
            "SELECT id, group_id, %s FROM university.schedule ";
    private static final String UPDATE_SQL =
            "UPDATE university.schedule SET group_id = ?";
    private static final String DELETE_SQL =
            "DELETE FROM university.lesson ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GroupScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(GroupSchedule groupSchedule) {
        String query = String.format(INSERT_SQL, COLUMNS);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.update(query, groupSchedule.getGroup().getId());
    }
    
    @Override
    public void addAll(List<GroupSchedule> groupSchedules) {
        String query = String.format(INSERT_SQL, COLUMNS);
        query = query.replace("[", "").replace("]", "");
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, groupSchedules.get(i).getGroup().getId());
            }
        
            @Override
            public int getBatchSize() {
                return groupSchedules.size();
            }
        });
    }
    
    @Override
    public GroupSchedule load(GroupSchedule groupSchedule) {
        String whereClause = "WHERE group_id = ?";
        String sql = String.format(SELECT_SQL, GroupDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new GroupScheduleMapper(),
                        groupSchedule.getGroup().getId())
                .stream().findAny().orElse(null);
    }
    
    @Override
    public GroupSchedule loadById(long id) {
        String whereClause = "WHERE id = ?";
        String sql = String.format(SELECT_SQL, GroupDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "") + whereClause;
        return jdbcTemplate.query(query, new GroupScheduleMapper(), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<GroupSchedule> loadAll() {
        String sql = String.format(SELECT_SQL, GroupDao.getColumns());
        final String query = sql.replace("[", "")
                .replace("]", "");
        return jdbcTemplate.query(query, new GroupScheduleMapper());
    }
    
    @Override
    public void update(long id, GroupSchedule groupSchedule) {
        jdbcTemplate.update(UPDATE_SQL, groupSchedule.getGroup().getId(), id);
    }
    
    @Override
    public void delete(GroupSchedule groupSchedule) {
        String whereClause = "WHERE group_id = ?";
        String query = DELETE_SQL + whereClause;
        jdbcTemplate.update(query, groupSchedule.getGroup().getId());
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
