package com.mark.college.service;

import com.mark.college.entity.Grade;
import com.mark.college.entity.Student;
import com.mark.college.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findGrades(Student student){
        List<Grade> grades = gradeRepository.findByStudent(student);

        if(!grades.isEmpty())
            return grades;
        return null;
    }
}