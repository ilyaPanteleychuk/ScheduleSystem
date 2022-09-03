package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class GroupDao implements CommonDao<Group> {
    
    private static final List<String> COLUMNS = List.of("groupnumber");
    private static final String INSERT_SQL =
            "INSERT INTO university.groups VALUES(?)";
    private static final String SELECT_SQL =
            "SELECT * FROM university.groups ";
    private static final String UPDATE_SQL =
            "UPDATE university.groups SET groupnumber = ? ";
    private static final String DELETE_SQL =
            "DELETE FROM university.groups ";
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(Group group) {
        jdbcTemplate.update(INSERT_SQL, group.getGroupNumber());
    }
    
    @Override
    public void addAll(List<Group> groupList) {
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, groupList.get(i).getGroupNumber());
            }
    
            @Override
            public int getBatchSize() {
                return groupList.size();
            }
        });
    }
    
    @Override
    public Group load(Group group) {
        String whereClause = "WHERE groupnumber = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Group.class),
                        group.getGroupNumber()).stream().findAny().orElse(null);
    }
    
    @Override
    public Group loadById(long id) {
        String whereClause = "WHERE id = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Group.class), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Group> loadAll() {
        return jdbcTemplate.query(SELECT_SQL, new BeanPropertyRowMapper<>(Group.class));
    }
    
    @Override
    public void update(long id, Group group) {
        String whereClause = "WHERE id = ?";
        final String updateSql = UPDATE_SQL + whereClause;
        jdbcTemplate.update(updateSql, group.getGroupNumber(), id);
    }
    
    @Override
    public void delete(Group group) {
        String whereClause = "WHERE groupnumber = ?";
        final String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql, group.getGroupNumber());
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
