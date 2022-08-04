package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


public class Exam extends Lesson implements Entity{
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public Exam(long id, UniversitySubject subject, Audience audience,
                Group group, Teacher teacher, LocalDateTime date, int order) {
        super(id, subject, audience, group, teacher, date, order);
    }
    
    public Exam(long id, UniversitySubject subject, Audience audience, Group group,
                Teacher teacher, LocalDateTime date, int order,
                LocalDateTime startTime, LocalDateTime endTime) {
        super(id, subject, audience, group, teacher, date, order);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
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
