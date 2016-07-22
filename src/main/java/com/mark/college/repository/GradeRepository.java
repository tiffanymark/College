package com.mark.college.repository;

import com.mark.college.entity.Grade;
import com.mark.college.entity.Student;
import com.mark.college.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query(value = "SELECT g FROM Grade g WHERE g.student = :student")
    List<Grade> findByStudent(@Param("student") Student student);

    Grade save(Grade grade);

    @Query(value = "SELECT g FROM Grade g WHERE g.subject = :subject AND g.student = :student")
    Grade findBySubjectAndStudent(@Param("subject") Subject subject, @Param("student") Student student);
}
