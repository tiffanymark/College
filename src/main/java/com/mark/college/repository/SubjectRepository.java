package com.mark.college.repository;

import com.mark.college.entity.Subject;
import com.mark.college.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Tiffany Mark on 06/07/2016.
 */

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT s FROM Subject s WHERE s.teacher = :teacher")
    List<Subject> findByTeacher(@Param("teacher") Teacher teacher);

}
