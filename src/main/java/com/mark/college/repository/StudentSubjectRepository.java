package com.mark.college.repository;

import com.mark.college.entity.Student;
import com.mark.college.entity.StudentSubject;
import com.mark.college.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, StudentSubject> {

    @Query(value = "SELECT ss FROM StudentSubject ss WHERE ss.student = :student")
    List<StudentSubject> findByStudent(@Param("student") Student student);

    @Query(value = "SELECT ss FROM StudentSubject ss WHERE ss.subject = :subject")
    List<StudentSubject> findBySubject(@Param("subject") Subject subject);

}
