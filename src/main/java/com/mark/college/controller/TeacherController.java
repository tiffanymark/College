package com.mark.college.controller;

import com.mark.college.entity.Subject;
import com.mark.college.entity.Teacher;
import com.mark.college.service.SubjectService;
import com.mark.college.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("id") int id){
        Teacher teacher = teacherService.findTeacher(id);

        List<Subject> subjects = subjectService.findSubjects(teacher);
        
        if(teacher != null && !subjects.isEmpty()){
            ModelAndView mvFindTeacher = new ModelAndView("findTeacher");
            mvFindTeacher.addObject("teacher", teacher);
            mvFindTeacher.addObject("subjects", subjects);
            return mvFindTeacher;
        }

        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Teacher not found. Try again!");
        return mvError;

    }

}
