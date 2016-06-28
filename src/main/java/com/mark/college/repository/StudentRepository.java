package com.mark.college.repository;

import com.mark.college.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT s FROM Student s WHERE s.id = :id")
    Student findById(@Param("id") int id);

}
