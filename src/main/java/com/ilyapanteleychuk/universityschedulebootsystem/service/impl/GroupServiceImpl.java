package com.ilyapanteleychuk.universityschedulebootsystem.service.impl;

import com.ilyapanteleychuk.universityschedulebootsystem.dao.GroupRepository;
import com.ilyapanteleychuk.universityschedulebootsystem.entity.Group;
import com.ilyapanteleychuk.universityschedulebootsystem.service.CommonService;
import com.ilyapanteleychuk.universityschedulebootsystem.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class GroupServiceImpl implements CommonService<Group>, GroupService {
    
    private final GroupRepository groupRepository;
    
    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    
    @Override
    @Transactional
    public List<Group> loadGroupsByFacultyId(long facultyId) {
        return groupRepository.findAllByFaculty_Id(facultyId);
    }
    
    @Override
    @Transactional
    public void save(Group group) {
        groupRepository.save(group);
    }
    
    @Override
    @Transactional
    public void saveAll(List<Group> groups) {
        groupRepository.saveAll(groups);
    }
    
    @Override
    @Transactional
    public Group findById(long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElseGet(() -> new Group(0, 0));
    }
    
    @Override
    @Transactional
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
    
    @Override
    @Transactional
    public void update(Group group) {
        groupRepository.save(group);
    }
    
    @Override
    @Transactional
    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }
}
