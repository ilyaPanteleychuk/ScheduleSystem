package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Schedule implements Entity {

    private List<Lesson> lessons;
    
    public Schedule() {
    }
    
    public Schedule(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    
    public void addLesson(Lesson lesson){
        if(lessons == null){
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
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
                "lessons=" + lessons +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(lessons, schedule.lessons);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(lessons);
    }
}
