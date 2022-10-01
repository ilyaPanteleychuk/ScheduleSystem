package com.ilyapanteleychuk.foxminded.universityschedule.service.impl;

import com.ilyapanteleychuk.foxminded.universityschedule.dao.impl.GroupDaoImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.Group;
import com.ilyapanteleychuk.foxminded.universityschedule.service.CommonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class GroupServiceImpl implements CommonService<Group>, GroupService {
    
    private final GroupDaoImpl groupDaoImpl;
    
    @Autowired
    public GroupServiceImpl(GroupDaoImpl groupDaoImpl) {
        this.groupDaoImpl = groupDaoImpl;
    }
    
    @Override
    @Transactional
    public List<Group> loadGroupsByFacultyId(long facultyId) {
        return groupDaoImpl.loadGroupsByFacultyId(facultyId);
    }
    
    @Override
    @Transactional
    public void add(Group group) {
        groupDaoImpl.save(group);
    }
    
    @Override
    @Transactional
    public void addAll(List<Group> groups) {
        groupDaoImpl.saveAll(groups);
    }
    
    @Override
    @Transactional
    public Group loadById(long id) {
        return groupDaoImpl.loadById(id);
    }
    
    @Override
    @Transactional
    public List<Group> loadAll() {
        return groupDaoImpl.loadAll();
    }
    
    @Override
    @Transactional
    public void update(Group group) {
        groupDaoImpl.update(group);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        groupDaoImpl.deleteById(id);
    }
}
