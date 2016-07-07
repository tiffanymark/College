package com.mark.college.service;

import com.mark.college.entity.Teacher;
import com.mark.college.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tiffany Mark on 07/07/2016.
 */

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findTeacher(int id){

        Teacher teacher = teacherRepository.findById(id);

        if(teacher != null)
            return teacher;
        return null;
    }


}
