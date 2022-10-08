package com.ilyapanteleychuk.foxminded.universityschedule.service;

import java.util.List;


public interface CommonService <T> {
    
    void save(T t);
    
    void saveAll(List<T> t);
    
    T findById(long id);
    
    List<T> findAll();
    
    void update(T t);
    
    void deleteById(long id);
}
