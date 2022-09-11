package com.ilyapanteleychuk.foxminded.universityschedule.service;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Entity;
import java.util.List;


public interface CommonService <T extends Entity> {
    
    void add(T t);
    
    void addAll(List<T> t);
    
    T load(T t);
    
    T loadById(long id);
    
    List<T> loadAll();
    
    void update(long id, T t);
    
    void delete(T t);
    
    void deleteById(long id);
}