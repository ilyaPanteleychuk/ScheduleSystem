package com.ilyapanteleychuk.foxminded.universityschedule.entity;

import java.util.Date;


public class Practice extends Lesson implements Entity{
    
    public Practice(long id, UniversitySubject subject, Audience audience, Group group,
                    Teacher teacher, Date date, int order) {
        super(id, subject, audience, group, teacher, date, order);
    }
}
