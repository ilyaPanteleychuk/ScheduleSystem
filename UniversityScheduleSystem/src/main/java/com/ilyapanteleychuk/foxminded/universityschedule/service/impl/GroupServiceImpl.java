package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GroupServiceImpl implements CommonService<Group>, GroupService {
    
    private final GroupDaoImpl groupDaoImpl;
    
    @Autowired
    public GroupServiceImpl(GroupDaoImpl groupDaoImpl) {
        this.groupDaoImpl = groupDaoImpl;
    }
    
    @Override
    public List<Group> loadGroupsByFacultyId(long facultyId) {
        return groupDaoImpl.loadGroupsByFacultyId(facultyId);
    }
    
    @Override
    public void add(Group group) {
        groupDaoImpl.add(group);
    }
    
    @Override
    public void addAll(List<Group> groups) {
        groupDaoImpl.addAll(groups);
    }
    
    @Override
    public Group load(Group group) {
        return groupDaoImpl.load(group);
    }
    
    @Override
    public Group loadById(long id) {
        return groupDaoImpl.loadById(id);
    }
    
    @Override
    public List<Group> loadAll() {
        return groupDaoImpl.loadAll();
    }
    
    @Override
    public void update(long id, Group group) {
        groupDaoImpl.update(id, group);
    }
    
    @Override
    public void delete(Group group) {
        groupDaoImpl.delete(group);
    }
    
    @Override
    public void deleteById(long id) {
        groupDaoImpl.deleteById(id);
    }
}
