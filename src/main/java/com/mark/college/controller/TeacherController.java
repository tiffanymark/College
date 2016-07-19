package com.mark.college.controller;

import com.mark.college.entity.StudentSubject;
import com.mark.college.entity.Subject;
import com.mark.college.entity.Teacher;
import com.mark.college.service.StudentSubjectService;
import com.mark.college.service.SubjectService;
import com.mark.college.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentSubjectService studentSubjectService;

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("id") int id){
        Teacher teacher = teacherService.findTeacher(id);
        List<Subject> subjects = subjectService.findSubjects(teacher);

        if(teacher != null && !subjects.isEmpty()){
            ModelAndView mvFindTeacher = new ModelAndView("findTeacher");
            mvFindTeacher.addObject("subjects", subjects);
            mvFindTeacher.addObject("teacher", teacher);
            return mvFindTeacher;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Teacher not found. Try again!");
        return mvError;
    }

    @RequestMapping(value = "teacher/{id}/{subjectId}/students", method = RequestMethod.GET)
    public ModelAndView findStudents(@PathVariable("id") int id, @PathVariable("subjectId") int subjectId){
        Teacher teacher = teacherService.findTeacher(id);
        Subject subject = subjectService.find(subjectId);
        List<StudentSubject> studentSubjects = studentSubjectService.findStudents(subject);

        if(teacher != null && subject != null && !studentSubjects.isEmpty()){
            ModelAndView mvFindStudents = new ModelAndView("findStudentsBySubject");
            mvFindStudents.addObject("teacher", teacher);
            mvFindStudents.addObject("subject", subject);
            mvFindStudents.addObject("ss", studentSubjects);
            return mvFindStudents;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Students not found. Try again!");
        return mvError;
    }

    @RequestMapping(value = "/teacher-login", method = RequestMethod.GET)
    public ModelAndView teacherLoginPage(){
        return new ModelAndView("teacherLogin");
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public ModelAndView teacherLogin(@ModelAttribute Teacher teacher){
        Teacher teacherAuth = teacherService.auth(teacher.getId(),teacher.getPassword());

        if(teacherAuth != null){
            ModelAndView mvTeacherMenu = new ModelAndView("teacherHomepage");
            mvTeacherMenu.addObject("teacherObj", teacherAuth);
            return mvTeacherMenu;
        }
        return new ModelAndView("teacherLogin");
    }

    @RequestMapping(value = "/teacher-homepage", method = RequestMethod.GET)
    public ModelAndView teacherHomepage(){
        return new ModelAndView("teacherHomepage");
    }

}
