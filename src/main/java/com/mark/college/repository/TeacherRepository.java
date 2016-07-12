package com.mark.college.repository;

import com.mark.college.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Tiffany Mark on 07/07/2016.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query(value = "SELECT t FROM Teacher t WHERE t.id = :id")
    Teacher findById(@Param("id") int id);

    @Query(value = "SELECT t FROM Teacher t WHERE t.id = :id AND t.password = :password")
    Teacher findByIdAndPassword(@Param("id") int id, @Param("password") String password);

}
