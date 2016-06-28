package com.mark.college.repository;

import com.mark.college.entity.Grade;
import com.mark.college.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query(value = "SELECT g FROM Grade g WHERE g.student = :student")
    List<Grade> findByStudent(@Param("student") Student student);

}
