package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="university.audience")
public class Audience {
    
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "audience_number")
    private int audienceNumber;
    
    public Audience() {
    }
    
    public Audience(long id, int audienceNumber) {
        this.id = id;
        this.audienceNumber = audienceNumber;
    }
    
    public Audience(int audienceNumber) {
        this.audienceNumber = audienceNumber;
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
    
    @Override
    public String toString() {
        return "Audience{" +
                "id=" + id +
                ", audienceNumber=" + audienceNumber +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audience audience = (Audience) o;
        return id == audience.id && audienceNumber == audience.audienceNumber;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, audienceNumber);
    }
}
