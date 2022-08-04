package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Objects;


public class Teacher {

    private long id;
    private String firstName;
    private String lastName;
    private Schedule schedule;
    
    public Teacher() {
    }
    
    public Teacher(long id, String firstName, String lastName, Schedule schedule) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schedule = schedule;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schedule=" + schedule +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && Objects.equals(firstName, teacher.firstName)
                && Objects.equals(lastName, teacher.lastName)
                && Objects.equals(schedule, teacher.schedule);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, schedule);
    }
}
