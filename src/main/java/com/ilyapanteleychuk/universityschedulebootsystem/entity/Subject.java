package com.ilyapanteleychuk.universityschedulebootsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "subject")
public class Subject {
    
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "title")
    private String title;
    
    public Subject() {
    }
    
    public Subject(String title) {
        this.title = title;
    }
    
    public Subject(long id, String title) {
        this.id = id;
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
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject that = (Subject) o;
        return id == that.id && Objects.equals(title, that.title);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
