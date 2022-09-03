package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TeacherSchedule extends Schedule{
    
    private Teacher teacher;
    private List<TeacherLesson> teacherLessons;
    
    public TeacherSchedule() {
    }
    
    public TeacherSchedule(long id) {
        super(id);
    }
    
    public TeacherSchedule(long id, Teacher teacher) {
        super(id);
        this.teacher = teacher;
    }
    
    public void addLesson(TeacherLesson teacherLesson){
        if(teacherLessons == null){
            teacherLessons = new ArrayList<>();
        }
        teacherLessons.add(teacherLesson);
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public List<TeacherLesson> getTeacherLessons() {
        return teacherLessons;
    }
    
    public void setTeacherLessons(List<TeacherLesson> teacherLessons) {
        this.teacherLessons = teacherLessons;
    }
    
    @Override
    public String toString() {
        return "TeacherSchedule{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", teacherLessons=" + teacherLessons +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TeacherSchedule that = (TeacherSchedule) o;
        return Objects.equals(teacher, that.teacher)
                && Objects.equals(teacherLessons, that.teacherLessons);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacher, teacherLessons);
    }
}
