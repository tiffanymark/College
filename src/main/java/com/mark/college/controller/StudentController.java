package com.mark.college.controller;

import com.mark.college.entity.Student;
import com.mark.college.entity.StudentSubject;
import com.mark.college.service.StudentService;
import com.mark.college.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

@Controller
@SessionAttributes("studentObj")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentSubjectService studentSubjectService;

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("id") int id){

        Student student = studentService.findStudent(id);

        List<StudentSubject> studentSubjects = studentSubjectService.findSubjects(student);

        if(student != null && !studentSubjects.isEmpty()){
            ModelAndView mvFindStudent = new ModelAndView("findStudent");
            mvFindStudent.addObject("student", student);
            mvFindStudent.addObject("studentSubjects", studentSubjects);
            return mvFindStudent;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Student not found. Try again!");
        return mvError;
    }

    @RequestMapping(value = "/student/login", method = RequestMethod.GET)
    public ModelAndView studentLoginPage(){
        return new ModelAndView("studentLogin");
    }

    @RequestMapping(value = "/student/menu", method = RequestMethod.POST)
    public ModelAndView studentLogin(@ModelAttribute Student student){
        Student studentAuth = studentService.auth(student.getId(),student.getPassword());

        if(studentAuth != null) {
            ModelAndView mvStudentMenu = new ModelAndView("studentMenu");
            mvStudentMenu.addObject("studentObj", studentAuth);
            return mvStudentMenu;
        }
        return new ModelAndView("studentLogin");

    }

}
