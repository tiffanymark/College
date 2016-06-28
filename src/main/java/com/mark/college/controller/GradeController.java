package com.mark.college.controller;

import com.mark.college.entity.Grade;
import com.mark.college.entity.Student;
import com.mark.college.service.GradeService;
import com.mark.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */

@Controller
@RequestMapping(value = "/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("studentId") int id){
        Student student = studentService.findStudent(id);
        List<Grade> grades = gradeService.findGrades(student);

        if(!grades.isEmpty()){
            ModelAndView mvFindGrades = new ModelAndView("findGrades");
            mvFindGrades.addObject("name",student.getName());
            mvFindGrades.addObject("id",student.getId());
            mvFindGrades.addObject("grades",grades);
            return mvFindGrades;
        }

        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Try again");
        return mvError;
    }


}
