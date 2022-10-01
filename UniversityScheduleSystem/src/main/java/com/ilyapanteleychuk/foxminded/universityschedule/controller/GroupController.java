package com.ilyapanteleychuk.foxminded.universityschedule.controller;

import com.ilyapanteleychuk.foxminded.universityschedule.entity.GroupLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.entity.TeacherLesson;
import com.ilyapanteleychuk.foxminded.universityschedule.service.LessonService;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.GroupLessonServiceImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.GroupServiceImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.TeacherLessonServiceImpl;
import com.ilyapanteleychuk.foxminded.universityschedule.utils.DateFormatter;
import com.ilyapanteleychuk.foxminded.universityschedule.service.impl.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
public class GroupController {
    
    private final GroupServiceImpl groupServiceImpl;
    private final FacultyService facultyService;
    private final GroupLessonServiceImpl groupLessonService;
    private final LessonService<TeacherLesson> teacherLessonLessonService;
    
    @Autowired
    public GroupController(GroupServiceImpl groupServiceImpl,
                           FacultyService facultyService,
                           GroupLessonServiceImpl groupLessonService,
                           TeacherLessonServiceImpl teacherLessonLessonService) {
        this.groupServiceImpl = groupServiceImpl;
        this.facultyService = facultyService;
        this.groupLessonService = groupLessonService;
        this.teacherLessonLessonService = teacherLessonLessonService;
    }
    
    @RequestMapping("/")
    public String startView(Model model){
        model.addAttribute("testLesson", groupLessonService.loadById(6));
        return "startView";
    }
    
    @RequestMapping("/facultyPage")
    public String facultyPage(Model model){
        model.addAttribute("facultyList", facultyService.loadAll());
        return "facultyPage";
    }
    
    @RequestMapping("/groupPage")
    public String groupPage(@RequestParam("facultyId") long facultyId, Model model){
        model.addAttribute("groups",
                groupServiceImpl.loadGroupsByFacultyId(facultyId));
        return "groupPage";
    }
    
    @RequestMapping("/groupScheduleResult")
    public String groupScheduleResult(@RequestParam("groupNumber") long groupId,
                                      Model model){
        List<LocalDate> currentWeek = DateFormatter.getCurrentWeek();
        model.addAttribute("weekDates", currentWeek);
        Map<String, List<GroupLesson>> lessons =
                groupLessonService.loadLessonsPerWeek(groupId);
        model.addAttribute("lessons", lessons);
        return "groupSchedule";
    }
    
    @RequestMapping("/teacherForm")
    public String teacherForm(){
        return "teacherForm";
    }
    
    @RequestMapping("/teacherScheduleResult")
    public String teacherScheduleResult(@RequestParam("teacherID") long teacherId,
                                        Model model) {
        List<LocalDate> currentWeek = DateFormatter.getCurrentWeek();
        model.addAttribute("weekDates", currentWeek);
        Map<String, List<TeacherLesson>> lessons =
                teacherLessonLessonService.loadLessonsPerWeek(teacherId);
        model.addAttribute("lessons", lessons);
        return "teacherSchedule";
    }
}
