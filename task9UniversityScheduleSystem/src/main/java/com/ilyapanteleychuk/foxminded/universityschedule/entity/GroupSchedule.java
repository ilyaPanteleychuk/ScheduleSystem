package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GroupSchedule extends Schedule {
    
    private Group group;
    private List<GroupLesson> groupLessons;
    
    public GroupSchedule() {
    }
    
    public GroupSchedule(long id) {
        super(id);
    }
    
    public GroupSchedule(Group group) {
        this.group = group;
    }
    
    public GroupSchedule(long id, Group group) {
        super(id);
        this.group = group;
    }
    
    public void addLesson(GroupLesson groupLesson){
        if(groupLessons == null){
            groupLessons = new ArrayList<>();
        }
        groupLessons.add(groupLesson);
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    public List<GroupLesson> getGroupLessons() {
        return groupLessons;
    }
    
    public void setGroupLessons(List<GroupLesson> groupLessons) {
        this.groupLessons = groupLessons;
    }
    
    @Override
    public String toString() {
        return "GroupSchedule{" +
                "id=" + id +
                ", group=" + group +
                ", groupLessons=" + groupLessons +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GroupSchedule that = (GroupSchedule) o;
        return Objects.equals(group, that.group)
                && Objects.equals(groupLessons, that.groupLessons);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, groupLessons);
    }
}
