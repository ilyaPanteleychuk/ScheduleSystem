package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "university.lesson")
public class GroupLesson extends Lesson {
    
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    
    public GroupLesson() {
    }
    
    public GroupLesson(long id, Subject subject, Audience audience,
                       LocalDate date, String type, int order, Teacher teacher) {
        super(id, subject, audience, date, type, order);
        this.teacher = teacher;
    }
    
    public GroupLesson(Subject subject, Audience audience,
                       LocalDate date, String type, int order, Teacher teacher) {
        super(subject, audience, date, type, order);
        this.teacher = teacher;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public String toString() {
        return "GroupLesson{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", subject=" + subject +
                ", audience=" + audience +
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
        GroupLesson that = (GroupLesson) o;
        return Objects.equals(teacher, that.teacher);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacher);
    }
}
