package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Objects;


public class Faculty implements Entity{
    
    private long id;
    private String title;
    
    public Faculty() {
    }
    
    public Faculty(long id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public Faculty(String title) {
        this.title = title;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && Objects.equals(title, faculty.title);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
