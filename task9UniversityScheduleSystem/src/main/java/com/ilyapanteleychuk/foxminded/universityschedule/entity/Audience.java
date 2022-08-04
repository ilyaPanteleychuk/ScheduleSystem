package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Objects;


public class Audience implements Entity {
    
    private long id;
    private int audienceNumber;
    private int audienceCapacity;
    
    public Audience() {
    }
    
    public Audience(long id, int audienceNumber, int audienceCapacity) {
        this.id = id;
        this.audienceNumber = audienceNumber;
        this.audienceCapacity = audienceCapacity;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public int getAudienceNumber() {
        return audienceNumber;
    }
    
    public void setAudienceNumber(int audienceNumber) {
        this.audienceNumber = audienceNumber;
    }
    
    public int getAudienceCapacity() {
        return audienceCapacity;
    }
    
    public void setAudienceCapacity(int audienceCapacity) {
        this.audienceCapacity = audienceCapacity;
    }
    
    @Override
    public String toString() {
        return "Audience{" +
                "id=" + id +
                ", audienceNumber=" + audienceNumber +
                ", audienceCapacity=" + audienceCapacity +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audience audience = (Audience) o;
        return id == audience.id && audienceNumber == audience.audienceNumber
                && audienceCapacity == audience.audienceCapacity;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, audienceNumber, audienceCapacity);
    }
}
