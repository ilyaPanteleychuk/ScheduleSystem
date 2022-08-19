package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Entity;

import java.util.List;


public interface CommonDao <T extends Entity> {

    void add(T t);
    
    void addAll(List<T> t);
    
    T get(T t);
    
    T getById(int id);
    
    List<T> getAll();
    
    void update(int id, T t);
    
    void delete(T t);
    
    void deleteById(int id);
    
}
