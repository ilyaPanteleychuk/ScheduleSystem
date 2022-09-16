package com.ilyapanteleychuk.foxminded.universityschedule.controller;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.FacultyService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.GroupServiceImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.ScheduleFormatterService;
import com.ilyapanteleychuk.foxminded.universityschedule.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
public class HomePageController {
    
    private final ScheduleFormatterService scheduleFormatterService;
    private final GroupServiceImpl groupServiceImpl;
    private final FacultyService facultyService;
    
    @Autowired
    public HomePageController(ScheduleFormatterService scheduleFormatterService,
                              GroupServiceImpl groupServiceImpl,
                              FacultyService facultyService) {
        this.scheduleFormatterService = scheduleFormatterService;
        this.groupServiceImpl = groupServiceImpl;
        this.facultyService = facultyService;
    }
    
    @RequestMapping("/")
    public String tablePage(Model model){
        model.addAttribute("facultyList", facultyService.loadAll());
        return "index";
    }
    
    @RequestMapping("/groupPage")
    public String groupPage(@RequestParam("facultyId") long facultyId, Model model){
        model.addAttribute("groups",
                groupServiceImpl.loadGroupsByFacultyId(facultyId));
        return "groupPage";
    }
    
    @RequestMapping("/tableResult")
    public String tableResult(@RequestParam("groupNumber") long groupId, Model model){
        List<LocalDate> currentWeek = DateFormatter.getCurrentWeek();
        model.addAttribute("weekDates", currentWeek);
        Map<String, List<GroupLesson>> lessons =
                scheduleFormatterService.formatLessons(groupId);
        model.addAttribute("lessons", lessons);
        return "tablePage";
    }
}
