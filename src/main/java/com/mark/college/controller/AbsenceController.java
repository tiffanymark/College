package com.mark.college.controller;

import com.mark.college.entity.Absence;
import com.mark.college.entity.Student;
import com.mark.college.service.AbsenceService;
import com.mark.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */

@Controller
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/absence/{studentId}", method = RequestMethod.GET)
    public ModelAndView find(@PathVariable("studentId") int id){
        Student student = studentService.findStudent(id);
        List<Absence> absences = absenceService.findAbsences(student);

        if(student != null && !absences.isEmpty()){
            ModelAndView mvFindAbsences = new ModelAndView("findAbsences");
            mvFindAbsences.addObject("name",student.getName());
            mvFindAbsences.addObject("id", id);
            mvFindAbsences.addObject("absences",absences);
            return mvFindAbsences;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg","Student not found. Try again!");
        return mvError;
    }

    @RequestMapping(value = "/absence/insert", method = RequestMethod.GET)
    public ModelAndView insertPAge(){
        return new ModelAndView("insertAbsence");
    }

    @RequestMapping(value = "/absence/inserted", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Absence absence){
        int studentId = absence.getStudent().getId();
        Student student = studentService.findStudent(studentId);

        Boolean newAbsence = absenceService.insertAbsences(absence);

        if(newAbsence){
            ModelAndView mvInsertedAbsence = new ModelAndView("insertedAbsence");
            mvInsertedAbsence.addObject("name", student.getName());
            mvInsertedAbsence.addObject("id", studentId);
            mvInsertedAbsence.addObject("subject", absence.getSubject());
            mvInsertedAbsence.addObject("quantity", absence.getQuantity());
            return mvInsertedAbsence;
        }

        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg","Absence was not inserted. Try again!");
        return mvError;
    }

}
