package com.mark.college.service;

import com.mark.college.entity.Absence;
import com.mark.college.entity.Student;
import com.mark.college.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public List<Absence> findAbsences(Student student){
        List<Absence> absences = absenceRepository.findByStudent(student);

        if(!absences.isEmpty())
            return absences;
        return null;
    }

}
