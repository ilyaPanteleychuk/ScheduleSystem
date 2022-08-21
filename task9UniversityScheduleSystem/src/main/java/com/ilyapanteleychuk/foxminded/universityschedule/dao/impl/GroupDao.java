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
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("groupnumber");
    
    @Autowired
    public GroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void add(Group group) {
        final String insertSql = "INSERT INTO university.groups VALUES(?)";
        jdbcTemplate.update(insertSql, group.getGroupNumber());
    }
    
    @Override
    public void addAll(List<Group> groupList) {
        final String insertSql = "INSERT INTO university.groups VALUES(?)";
        jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
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
    public Group get(Group group) {
        final String selectSql = "SELECT * FROM university.groups " +
                "WHERE groupnumber = ?";
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Group.class),
                        group.getGroupNumber()).stream().findAny().orElse(null);
    }
    
    @Override
    public Group getById(int id) {
        final String selectSql = "SELECT * FROM university.groups WHERE id = ?";
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Group.class), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Group> getAll() {
        final String selectSql = "SELECT * FROM university.groups";
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Group.class));
    }
    
    @Override
    public void update(int id, Group group) {
        final String updateSql = "UPDATE university.groups SET groupnumber = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, group.getGroupNumber(), id);
    }
    
    @Override
    public void delete(Group group) {
        final String deleteSql = "DELETE FROM university.groups WHERE groupnumber = ?";
        jdbcTemplate.update(deleteSql, group.getGroupNumber());
    }
    
    @Override
    public void deleteById(int id) {
        final String deleteSql = "DELETE FROM university.groups WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
    
    public static List<String> getColumns(){
        return columns;
    }
}
