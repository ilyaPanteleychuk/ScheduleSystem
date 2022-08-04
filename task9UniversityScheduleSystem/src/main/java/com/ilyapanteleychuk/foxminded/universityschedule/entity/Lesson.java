package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Date;
import java.util.Objects;


public abstract class Lesson implements Entity{
    
    protected long id;
    protected UniversitySubject subject;
    protected Audience audience;
    protected Group group;
    protected Teacher teacher;
    protected Date date;
    protected int order;
    
    protected Lesson(long id, UniversitySubject subject, Audience audience,
                  Group group, Teacher teacher, Date date, int order) {
        this.id = id;
        this.subject = subject;
        this.audience = audience;
        this.group = group;
        this.teacher = teacher;
        this.date = date;
        this.order = order;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public UniversitySubject getSubject() {
        return subject;
    }
    
    public void setSubject(UniversitySubject subject) {
        this.subject = subject;
    }
    
    public Audience getAudience() {
        return audience;
    }
    
    public void setAudience(Audience audience) {
        this.audience = audience;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getOrder() {
        return order;
    }
    
    public void setOrder(int order) {
        this.order = order;
    }
    
    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subject=" + subject +
                ", audience=" + audience +
                ", group=" + group +
                ", teacher=" + teacher +
                ", date=" + date +
                ", order=" + order +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id && order == lesson.order
                && Objects.equals(subject, lesson.subject)
                && Objects.equals(audience, lesson.audience)
                && Objects.equals(group, lesson.group)
                && Objects.equals(teacher, lesson.teacher)
                && Objects.equals(date, lesson.date);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, subject, audience, group, teacher, date, order);
    }
}
