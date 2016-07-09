package com.mark.college.controller;

import com.mark.college.entity.Student;
import com.mark.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tiffany Mark on 08/07/2016.
 */

@Controller
@SessionAttributes("studentObj")
public class LoginController {

    @Autowired
    private StudentService studentService;

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
