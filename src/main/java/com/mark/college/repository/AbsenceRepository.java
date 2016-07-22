package com.mark.college.repository;

import com.mark.college.entity.Absence;
import com.mark.college.entity.Student;
import com.mark.college.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */
public interface AbsenceRepository extends JpaRepository<Absence, Integer> {

    @Query(value = "SELECT a FROM Absence a WHERE a.student = :student")
    List<Absence> findByStudent(@Param("student") Student student);

    Absence save(Absence absence);

    @Query(value = "SELECT a FROM Absence a WHERE a.subject = :subject AND a.student = :student")
    Absence findByStudentAndSubject(@Param("subject") Subject subject, @Param("student") Student student);

}
