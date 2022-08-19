package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Objects;


public class Subject implements Entity {
    
    private long id;
    private String subjectTitle;
    
    public Subject() {
    }
    
    public Subject(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
    
    public Subject(long id, String subjectTitle) {
        this.id = id;
        this.subjectTitle = subjectTitle;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getSubjectTitle() {
        return subjectTitle;
    }
    
    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
    
    @Override
    public String toString() {
        return "UniversitySubject{" +
                "id=" + id +
                ", subjectTitle='" + subjectTitle + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject that = (Subject) o;
        return id == that.id && Objects.equals(subjectTitle, that.subjectTitle);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, subjectTitle);
    }
}
