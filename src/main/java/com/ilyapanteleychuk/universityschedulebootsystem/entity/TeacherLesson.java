package com.ilyapanteleychuk.universityschedulebootsystem.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "lesson")
public class TeacherLesson extends Lesson {
    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    
    public TeacherLesson() {
    }
    
    public TeacherLesson(long id, Subject subject, Audience audience,
                         LocalDate date, String type, int order, Group group) {
        super(id, subject, audience, date, type, order);
        this.group = group;
    }
    
    public TeacherLesson(Subject subject, Audience audience,
                         LocalDate date, String type, int order, Group group) {
        super(subject, audience, date, type, order);
        this.group = group;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    @Override
    public String toString() {
        return "TeacherLesson{" +
                "id=" + id +
                ", subject=" + subject +
                ", audience=" + audience +
                ", group=" + group +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", order=" + order +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TeacherLesson that = (TeacherLesson) o;
        return Objects.equals(group, that.group);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group);
    }
}
