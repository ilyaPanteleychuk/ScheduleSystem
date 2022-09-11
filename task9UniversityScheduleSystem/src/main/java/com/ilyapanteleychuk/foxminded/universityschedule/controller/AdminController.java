package com.ilyapanteleychuk.foxminded.universityschedule.controller;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.Faculty;
import com.ilyapanteleychuk.foxminded.universityschedule.service.GroupLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresource.ServletContextTemplateResource;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {
    
    private final GroupLessonService groupLessonService;
    
    @Autowired
    public AdminController(GroupLessonService groupLessonService) {
        this.groupLessonService = groupLessonService;
    }
    
    @RequestMapping("/")
    public String tablePage(Model model){
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(new Faculty("FRGTB"));
        facultyList.add(new Faculty("FIT"));
        facultyList.add(new Faculty("FTM"));
        model.addAttribute("faculties", facultyList);
        return "index";
    }
}
