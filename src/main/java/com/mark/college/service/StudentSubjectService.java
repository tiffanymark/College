package com.mark.college.service;

import com.mark.college.entity.Student;
import com.mark.college.entity.StudentSubject;
import com.mark.college.repository.StudentSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */

@Service
public class StudentSubjectService {

    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    public List<StudentSubject> findSubjects(Student student){
        List<StudentSubject> studentSubjects = studentSubjectRepository.findByStudent(student);

        if(!studentSubjects.isEmpty())
            return studentSubjects;
        return null;
    }

}

