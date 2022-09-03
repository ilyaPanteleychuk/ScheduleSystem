package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupDao;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GroupService implements CommonService<Group> {
    
    private final GroupDao groupDao;
    
    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
    
    @Override
    public void add(Group group) {
        groupDao.add(group);
    }
    
    @Override
    public void addAll(List<Group> groups) {
        groupDao.addAll(groups);
    }
    
    @Override
    public Group load(Group group) {
        return groupDao.load(group);
    }
    
    @Override
    public Group loadById(long id) {
        return groupDao.loadById(id);
    }
    
    @Override
    public List<Group> loadAll() {
        return groupDao.loadAll();
    }
    
    @Override
    public void update(long id, Group group) {
        groupDao.update(id, group);
    }
    
    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }
    
    @Override
    public void deleteById(long id) {
        groupDao.deleteById(id);
    }
}
