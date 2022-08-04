package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Date;
import java.util.Objects;


public class Exam extends Lesson implements Entity{
    
    private Date startTime;
    private Date endTime;
    
    public Exam(long id, UniversitySubject subject, Audience audience,
                Group group, Teacher teacher, Date date, int order) {
        super(id, subject, audience, group, teacher, date, order);
    }
    
    public Exam(long id, UniversitySubject subject, Audience audience, Group group,
                Teacher teacher, Date date, int order, Date startTime, Date endTime) {
        super(id, subject, audience, group, teacher, date, order);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Override
    public String toString() {
        return "Exam{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Exam exam = (Exam) o;
        return Objects.equals(startTime, exam.startTime) && Objects.equals(endTime, exam.endTime);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startTime, endTime);
    }
}
