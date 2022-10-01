package com.ilyapanteleychuk.foxminded.universityschedule.service;

import java.util.List;


public interface CommonService <T> {
    
    void add(T t);
    
    void addAll(List<T> t);
    
    T loadById(long id);
    
    List<T> loadAll();
    
    void update(T t);
    
    void deleteById(long id);
}
