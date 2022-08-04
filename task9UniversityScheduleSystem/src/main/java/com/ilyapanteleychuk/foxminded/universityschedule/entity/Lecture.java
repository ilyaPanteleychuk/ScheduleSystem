package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Lecture extends Lesson implements Entity{

    private List<Group> groups;
    
    public Lecture(long id, UniversitySubject subject, Audience audience, Group group,
                   Teacher teacher, LocalDateTime date, int order) {
        super(id, subject, audience, group, teacher, date, order);
    }
    
    public Lecture(long id, UniversitySubject subject, Audience audience, Group group,
                   Teacher teacher, LocalDateTime date, int order, List<Group> groups) {
        super(id, subject, audience, group, teacher, date, order);
        this.groups = groups;
    }
    
    public void addGroup(Group group){
        if(groups == null){
            groups = new ArrayList<>();
        }
        groups.add(group);
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    @Override
    public String toString() {
        return "Lecture{" +
                "groups=" + groups +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecture lecture = (Lecture) o;
        return Objects.equals(groups, lecture.groups);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), groups);
    }
}
