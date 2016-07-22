package com.mark.college.controller;

import com.mark.college.entity.*;
import com.mark.college.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */

@Controller
@SessionAttributes("teacherObj")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentSubjectService studentSubjectService;

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private StudentService studentService;


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

    @RequestMapping(value = "/teacher-{id}-subjects", method = RequestMethod.GET)
    public ModelAndView teacherSubjects(@PathVariable("id") int id){
        Teacher teacher = teacherService.findTeacher(id);
        List<Subject> subjects = subjectService.findSubjects(teacher);

        if(!subjects.isEmpty()){
            ModelAndView mvTeacherSubjects = new ModelAndView("teacherSubjects");
            mvTeacherSubjects.addObject("subjects", subjects);
            return mvTeacherSubjects;
        }

        ModelAndView mvNoSubjects = new ModelAndView("teacherHomepage");
        return mvNoSubjects;
    }

    @RequestMapping(value = "teacher-{id}-subject-{subjectId}", method = RequestMethod.GET)
    public ModelAndView findStudents(@PathVariable("id") int id, @PathVariable("subjectId") int subjectId){
        Teacher teacher = teacherService.findTeacher(id);
        Subject subject = subjectService.find(subjectId);
        List<StudentSubject> studentSubjects = studentSubjectService.findStudents(subject);

        if(teacher != null && subject != null && !studentSubjects.isEmpty()){
            ModelAndView mvFindStudents = new ModelAndView("teacherStudents");
            mvFindStudents.addObject("subject", subject);
            mvFindStudents.addObject("ss", studentSubjects);
            return mvFindStudents;
        }
        ModelAndView mvError = new ModelAndView("error");
        mvError.addObject("msg", "Students not found. Try again!");
        return mvError;
    }

    @RequestMapping(value = "/teacher-{id}-absences-list", method = RequestMethod.GET)
    public ModelAndView allAbsListsPage(@PathVariable("id") int id){
        Teacher teacher = teacherService.findTeacher(id);
        List<Subject> subjects = subjectService.findSubjects(teacher);

        ModelAndView mvTeacherAllAbsLists = new ModelAndView("teacherAllAbsLists");
        mvTeacherAllAbsLists.addObject("subjects", subjects);
        return mvTeacherAllAbsLists;

    }

    @RequestMapping(value = "/teacher-{id}-absences-list-subject-{subjectId}", method = RequestMethod.GET)
    public ModelAndView absencesListPage(@PathVariable("id") int id, @PathVariable("subjectId") int subjectId){
        Teacher teacher = teacherService.findTeacher(id);
        Subject subject = subjectService.find(subjectId);
        List<StudentSubject> studentSubjects = studentSubjectService.findStudents(subject);
        List<Student> students = new ArrayList<>();
        List<Absence> absences = new ArrayList<>();

        for (StudentSubject ss : studentSubjects) {
            students.add(ss.getStudent());
        }

        for (Student student : students) {
            Absence absence = absenceService.findAbsence(subject, student);
            if(absence != null){
                absences.add(absence);
            }
            else{
                Absence absence2 = new Absence(subject, student, 0);
                absences.add(absence2);
            }
        }


        ModelAndView mvAbsenceListPage = new ModelAndView("teacherAbsencesList");
        mvAbsenceListPage.addObject("teacherObj", teacher);
        mvAbsenceListPage.addObject("subject", subject);
        mvAbsenceListPage.addObject("absences", absences);
        return mvAbsenceListPage;
    }

    @RequestMapping(value = "/teacher-absences-list-subject-inserted", method = RequestMethod.POST)
    public ModelAndView absencesList(HttpServletRequest request){

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        Subject subject = subjectService.find(subjectId);

        String[] absences = request.getParameterValues("absence");
        for(String abs : absences){
            if(abs != null){
                int studentId = Integer.parseInt(abs);
                Student student = studentService.findStudent(studentId);
                Absence absence = absenceService.findAbsence(subject, student);

                if(absence != null) {
                    absence.setQuantity(absence.getQuantity() + 2);
                    absenceService.insertAbsences(absence);
                }
                else {
                    Absence newAbsence = new Absence(subject,student,2);
                    absenceService.insertAbsences(newAbsence);
                }
            }
        }
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        return absencesListPage(teacherId, subjectId);
    }

    /*
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
    */

}
