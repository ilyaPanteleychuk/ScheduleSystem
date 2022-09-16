package com.ilyapanteleychuk.foxminded.universityschedule.dao.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.CommonDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Audience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class AudienceDao implements CommonDao<Audience> {
    
    private static final List<String> COLUMNS =
            List.of("audience.id", "audience_number");
    private static final String INSERT_SQL =
            "INSERT INTO university.audience VALUES(?, ?)";
    private static final String SELECT_SQL =
            "SELECT * FROM university.audience ";
    private static final String UPDATE_SQL =
            "UPDATE university.audience SET audience_number = ?, audience_capacity = ? ";
    private static final String DELETE_SQL =
            "DELETE FROM university.audience ";
    
    private final JdbcTemplate jdbcTemplate;
    
    
    @Autowired
    public AudienceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    public void add(Audience audience) {
        jdbcTemplate.update(INSERT_SQL, audience.getAudienceNumber(),
                audience.getAudienceCapacity());
    }
    
    @Override
    public void addAll(List<Audience> audienceList) {
        jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, audienceList.get(i).getAudienceNumber());
                ps.setInt(2, audienceList.get(i).getAudienceCapacity());
            }
    
            @Override
            public int getBatchSize() {
                return audienceList.size();
            }
        });
    }
    
    @Override
    public Audience load(Audience audience) {
        String whereClause = "WHERE audience_number = ? AND audience_capacity = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql,
                        new BeanPropertyRowMapper<>(Audience.class),
                        audience.getAudienceNumber(),
                        audience.getAudienceCapacity())
                .stream().findAny().orElse(null);
    }
    
    @Override
    public Audience loadById(long id) {
        String whereClause = "WHERE id = ?";
        final String selectSql = SELECT_SQL + whereClause;
        return jdbcTemplate.query(selectSql,
                        new BeanPropertyRowMapper<>(Audience.class), id)
                .stream().findAny().orElse(null);
    }
    
    @Override
    public List<Audience> loadAll() {
        return jdbcTemplate.query(SELECT_SQL,
                new BeanPropertyRowMapper<>(Audience.class));
    }
    
    @Override
    public void update(long id, Audience audience) {
        String whereClause = "WHERE id = ?";
        String updateSql = UPDATE_SQL + whereClause;
        jdbcTemplate.update(updateSql, audience.getAudienceNumber(),
                audience.getAudienceCapacity(), id);
    }
    
    @Override
    public void delete(Audience audience) {
        String whereClause = "WHERE audience_number = ? AND audience_capacity = ?";
        final String deleteSql = DELETE_SQL + whereClause;
        jdbcTemplate.update(deleteSql,
                audience.getAudienceNumber(), audience.getAudienceCapacity());
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
