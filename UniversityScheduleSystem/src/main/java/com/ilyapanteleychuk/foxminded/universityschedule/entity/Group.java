package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "university.groups")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "group_number")
    private int groupNumber;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    
    public Group() {
    }
    
    public Group(int groupNumber, Faculty faculty) {
        this.groupNumber = groupNumber;
        this.faculty = faculty;
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
    
    public Faculty getFaculty() {
        return faculty;
    }
    
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupNumber=" + groupNumber +
                ", faculty=" + faculty +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id && groupNumber == group.groupNumber
                && Objects.equals(faculty, group.faculty);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, groupNumber, faculty);
    }
}
