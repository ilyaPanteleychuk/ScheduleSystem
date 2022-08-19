package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.time.LocalDate;
import java.util.Objects;


public abstract class Lesson implements Entity {
    
    protected long id;
    protected Subject subject;
    protected Audience audience;
    protected LocalDate date;
    protected String type;
    protected int order;
    protected Schedule schedule;
    
    protected Lesson() {
    }
    
    protected Lesson(long id, Subject subject, Audience audience,
                  LocalDate date, String type, int order) {
        this.id = id;
        this.subject = subject;
        this.audience = audience;
        this.date = date;
        this.type = type;
        this.order = order;
    }
    
    protected Lesson(Subject subject, Audience audience,
                  LocalDate date, String type, int order) {
        this.subject = subject;
        this.audience = audience;
        this.date = date;
        this.type = type;
        this.order = order;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public Audience getAudience() {
        return audience;
    }
    
    public void setAudience(Audience audience) {
        this.audience = audience;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public int getOrder() {
        return order;
    }
    
    public void setOrder(int order) {
        this.order = order;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
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
        Lesson lesson = (Lesson) o;
        return id == lesson.id && order == lesson.order
                && Objects.equals(subject, lesson.subject)
                && Objects.equals(audience, lesson.audience)
                && Objects.equals(date, lesson.date)
                && Objects.equals(type, lesson.type)
                && Objects.equals(schedule, lesson.schedule);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, subject, audience, date, type, order, schedule);
    }
}
