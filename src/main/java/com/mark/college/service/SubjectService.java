package com.mark.college.service;

import com.mark.college.entity.Subject;
import com.mark.college.entity.Teacher;
import com.mark.college.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findSubjects(Teacher teacher){
        List<Subject> subjects = subjectRepository.findByTeacher(teacher);

        if(!subjects.isEmpty())
            return subjects;
        return null;

    }

    public Subject find(int id){
        Subject subject = subjectRepository.findById(id);

        if(subject != null)
            return subject;
        return null;
    }

}
