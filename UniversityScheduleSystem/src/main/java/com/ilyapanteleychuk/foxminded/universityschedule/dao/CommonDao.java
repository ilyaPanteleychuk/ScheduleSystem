package com.ilyapanteleychuk.foxminded.universityschedule.dao;

import java.util.List;


public interface CommonDao <T> {

    void save(T t);
    
    void saveAll(List<T> t);
    
    T loadById(long id);
    
    List<T> loadAll();
    
    void update(T t);
    
    void deleteById(long id);
}
