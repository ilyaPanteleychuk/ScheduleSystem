package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Schedule implements Entity {

    private long schedule_id;
    private List<Lesson> lessons;
    
    public Schedule() {
    }
    
    public Schedule(long schedule_id) {
        this.schedule_id = schedule_id;
    }
    
    public Schedule(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    
    public void addLesson(Lesson lesson){
        if(lessons == null){
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
        lesson.setSchedule(this);
    }
    
    public long getSchedule_id() {
        return schedule_id;
    }
    
    public void setSchedule_id(long schedule_id) {
        this.schedule_id = schedule_id;
    }
    
    public List<Lesson> getLessons() {
        return lessons;
    }
    
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    
    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + schedule_id +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return schedule_id == schedule.schedule_id && Objects.equals(lessons, schedule.lessons);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(schedule_id, lessons);
    }
}
