package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Objects;


public class Group implements Entity{
    
    private long id;
    private int groupNumber;
    private Schedule schedule;
    
    public Group() {
    }
    
    public Group(int groupNumber) {
        this.groupNumber = groupNumber;
    }
    
    public Group(long id, int groupNumber) {
        this.id = id;
        this.groupNumber = groupNumber;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public int getGroupNumber() {
        return groupNumber;
    }
    
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupNumber=" + groupNumber +
                ", schedule=" + schedule +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && groupNumber == group.groupNumber
                && Objects.equals(schedule, group.schedule);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, groupNumber, schedule);
    }
}
