package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.time.LocalDate;
import java.util.Objects;


public class TeacherLesson extends Lesson implements Entity{
    
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
                ", schedule=" + schedule +
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
