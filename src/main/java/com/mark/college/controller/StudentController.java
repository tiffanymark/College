package com.mark.college.controller;

import com.mark.college.entity.Student;
import com.mark.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("id") int id){

        Student student = studentService.findStudent(id);

        if(student != null){
            ModelAndView mvFindStudent = new ModelAndView("findStudent");
            mvFindStudent.addObject("id", id);
            mvFindStudent.addObject("name", student.getName());
            return mvFindStudent;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Student not found. Try again!");
        return mvError;
    }

}
