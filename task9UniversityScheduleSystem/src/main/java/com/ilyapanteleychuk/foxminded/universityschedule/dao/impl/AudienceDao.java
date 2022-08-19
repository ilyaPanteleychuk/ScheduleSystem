package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class AudienceDao implements CommonDao<Audience> {
    
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> columns = List.of("audience_number");
    
    @Autowired
    public AudienceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    public void add(Audience audience) {
        final String insertSql = "INSERT INTO university.audience VALUES(?, ?)";
        jdbcTemplate.update(insertSql, audience.getAudienceNumber(),
                audience.getAudienceCapacity());
    }
    
    @Override
    public void addAll(List<Audience> audienceList) {
        final String insertSql = "INSERT INTO university.audience VALUES(?, ?)";
        for(int i = 0; i <= audienceList.size(); i++){
            jdbcTemplate.update(insertSql, audienceList.get(i).getAudienceNumber(),
                    audienceList.get(i).getAudienceCapacity());
        }
    }
    
    @Override
    public Audience get(Audience audience) {
        final String selectSql = "SELECT * FROM university.audience " +
                "WHERE audience_number = ? AND audience_capacity = ?";
        return jdbcTemplate.query(selectSql, new Object[]{
                audience.getAudienceNumber(), audience.getAudienceCapacity()
                }, new BeanPropertyRowMapper<>(Audience.class))
                .stream().findAny().orElse(null);
    }
    
    @Override
    public Audience getById(int id) {
        final String selectSql = "SELECT * FROM university.audience WHERE id = ?";
        return jdbcTemplate.query(selectSql, new Object[]{id},
                        new BeanPropertyRowMapper<>(Audience.class))
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Audience> getAll() {
        final String select_sql = "SELECT * FROM university.audience";
        return jdbcTemplate.query(select_sql, new BeanPropertyRowMapper<>(Audience.class));
    }
    
    @Override
    public void update(int id, Audience audience) {
        final String updateSql = "UPDATE university.audience " +
                "SET audience_number = ?, audience_capacity = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, audience.getAudienceNumber(),
                audience.getAudienceCapacity(), id);
    }
    
    @Override
    public void delete(Audience audience) {
        final String deleteSql = "DELETE FROM university.audience " +
                "WHERE audience_number = ? AND audience_capacity = ?";
        jdbcTemplate.update(deleteSql,
                audience.getAudienceNumber(), audience.getAudienceCapacity());
    }
    
    @Override
    public void deleteById(int id) {
        final String deleteSql = "DELETE FROM university.audience WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);
    }
    
    public static List<String> getColumns(){
        return columns;
    }
}
